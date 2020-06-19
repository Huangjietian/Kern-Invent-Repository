package cn.kerninventor.tools.poibox.data.tabulation.validation;

import cn.kerninventor.tools.poibox.data.tabulation.context.ColumnDefinition;
import cn.kerninventor.tools.poibox.data.tabulation.context.TabulationBeanConfiguration;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.lang.annotation.Annotation;
import java.lang.annotation.AnnotationFormatError;
import java.lang.reflect.Constructor;
import java.util.Objects;

/**
 * <h1>中文注释</h1>
 * <p>
 *     抽象数据校验建造者, 是所有数据校验解析执行类的父类。
 *     如果需要自定义的注解生成excel文档的数据有效性校验，对应注解的解析执行类应该继承该类。
 *     同时，自定义的注解需要在类上注解@DataValid并指定自定义的解析执行类的class。
 *     注意，自定义解析执行类的构造方法必须严格按照父类的格式进行。
 * </p>
 *
 * @author Kern
 * @version 1.0
 */
public abstract class AbstractDvBuilder<A extends Annotation> {

    private A annotation;

    public A getAnnotation() {
        return annotation;
    }

    protected String getPromptBoxName() {
        return "prompt";
    }

    protected String getErrorBoxName() {
        return "error";
    }

    public AbstractDvBuilder(A annotation) {
        this.annotation = annotation;
    }

    protected abstract DataValidationConstraint createDvConstraint(DataValidationHelper dvHelper);

    protected abstract void setBoxMessage(DataValidation dataValidation);

    public void setDataValidation(TabulationBeanConfiguration tableContext, ColumnDefinition columnDefinition, Sheet sheet) {
        DataValidationHelper dvHelper = sheet.getDataValidationHelper();
        DataValidationConstraint dvConstraint = createDvConstraint(dvHelper);
        CellRangeAddressList dvRange = createRange(tableContext, columnDefinition);
        DataValidation dataValidation = dvHelper.createValidation(dvConstraint, dvRange);
        setBoxMessage(dataValidation);
        sheet.addValidationData(dataValidation);
    }

    protected CellRangeAddressList createRange(TabulationBeanConfiguration tableContext, ColumnDefinition columnDefinition) {
        return new CellRangeAddressList(
                tableContext.getTbodyFirstRowIndex(),
                (tableContext.getTbodyFirstRowIndex() + tableContext.getEffectiveRows() - 1),
                columnDefinition.getColumnIndex(),
                columnDefinition.getColumnIndex()
        );
    }

    public static AbstractDvBuilder getInstance(Annotation annotation) {
        Objects.requireNonNull(annotation);
        DataValid dataValid = annotation.getClass().getDeclaredAnnotation(DataValid.class);
        if (dataValid == null){
            throw new AnnotationFormatError("@DataValid was not present on the Data validation configuration annotation! Annotation name: " + annotation.getClass().getName());
        }
        Class<? extends AbstractDvBuilder> dvBuilderClass = dataValid.dvBuilder();
        AbstractDvBuilder abstractDvBuilder = null;
        try {
            Constructor<? extends AbstractDvBuilder> constructor = dvBuilderClass.getDeclaredConstructor(Annotation.class);
            abstractDvBuilder = constructor.newInstance(annotation);
        } catch (Exception e) {
            throw new IllegalArgumentException("The DvBuilder construction failed. Check that a constructor was provided as required!", e);
        }
        return abstractDvBuilder;
    }
}

