package cn.kerninventory.tools.excel.anno.function;

import cn.kerninventory.tools.excel.anno.handler.UnmatchStrategy;

/**
 * <p>
 *     注解到配置Bean的字段中，当对Excel进行映射读写时，进行元数据和视图数据的翻译。
 *     Writing time -> Meta data to view data.
 *     Reading time -> View data to Meta data.
 * </p>
 *
 * @author Kern
 */
public @interface Translate {

    /**
     * Enum type -> E>com.sport.competition.GenderEnum#code:com.sport.competition.GenderEnum#name
     * Bean static method type ->  S>com.sport.competition.DemoTranslate$translate
     * Runtime inject bean type -> B>translatorName
     * @return
     */
    String value();

    String tag() default "";

    UnmatchStrategy unmatchStrategy() default UnmatchStrategy.IGNORE;
}
