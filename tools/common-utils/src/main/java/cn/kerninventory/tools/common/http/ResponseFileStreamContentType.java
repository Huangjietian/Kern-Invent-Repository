package cn.kerninventory.tools.common.http;


import cn.kerninventory.tools.common.StringUtil;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <h1>中文注释</h1>
 * <p>
 *     响应文件流的ContentType枚举
 * </p>
 * @author Kern
 * @version 1.0
 */
public enum ResponseFileStreamContentType {

        _ALL("*","application/octet-stream"),
        _001( ".001","application/x-001"),
        _301( ".301","application/x-301" ),
        _323( ".323","text/h323"),
        _906( ".906","application/x-906" ),
        _907( ".907","drawing/907"),
        _a11( ".a11","application/x-a11" ),
        _acp( ".acp","audio/x-mei-aac"),
        _ai( ".ai","application/postscript" ),
        _aif( ".aif","audio/aiff"),
        _aifc( ".aifc","audio/aiff" ),
        _aiff( ".aiff","audio/aiff"),
        _anv( ".anv","application/x-anv" ),
        _asa( ".asa","text/asa"),
        _asf( ".asf","video/x-ms-asf" ),
        _asp( ".asp","text/asp"),
        _asx( ".asx","video/x-ms-asf" ),
        _au( ".au","audio/basic"),
        _avi( ".avi","video/avi" ),
        _awf( ".awf","application/vnd.adobe.workflow"),
        _biz( ".biz","text/xml" ),
        _bmp( ".bmp","application/x-bmp"),
        _bot( ".bot","application/x-bot" ),
        _c4t( ".c4t","application/x-c4t"),
        _c90( ".c90","application/x-c90" ),
        _cal( ".cal","application/x-cals"),
        _cat( ".cat","application/vnd.ms-pki.seccat" ),
        _cdf( ".cdf","application/x-netcdf"),
        _cdr( ".cdr","application/x-cdr" ),
        _cel( ".cel","application/x-cel"),
        _cer( ".cer","application/x-x509-ca-cert" ),
        _cg4( ".cg4","application/x-g4"),
        _cgm( ".cgm","application/x-cgm" ),
        _cit( ".cit","application/x-cit"),
        _class( ".class","java/*" ),
        _cml( ".cml","text/xml"),
        _cmp( ".cmp","application/x-cmp" ),
        _cmx( ".cmx","application/x-cmx"),
        _cot( ".cot","application/x-cot" ),
        _crl( ".crl","application/pkix-crl"),
        _crt( ".crt","application/x-x509-ca-cert" ),
        _csi( ".csi","application/x-csi"),
        _css( ".css","text/css" ),
        _cut( ".cut","application/x-cut"),
        _dbf( ".dbf","application/x-dbf" ),
        _dbm( ".dbm","application/x-dbm"),
        _dbx( ".dbx","application/x-dbx" ),
        _dcd( ".dcd","text/xml"),
        _dcx( ".dcx","application/x-dcx" ),
        _der( ".der","application/x-x509-ca-cert"),
        _dgn( ".dgn","application/x-dgn" ),
        _dib( ".dib","application/x-dib"),
        _dll( ".dll","application/x-msdownload" ),
        _doc( ".doc","application/msword"),
        _dot( ".dot","application/msword" ),
        _drw( ".drw","application/x-drw"),
        _dtd( ".dtd","text/xml" ),
        _dwf( ".dwf","Model/vnd.dwf"),
        _dwfx( ".dwf","application/x-dwf" ),
        _dwg( ".dwg","application/x-dwg"),
        _dxb( ".dxb","application/x-dxb" ),
        _dxf( ".dxf","application/x-dxf"),
        _edn( ".edn","application/vnd.adobe.edn" ),
        _emf( ".emf","application/x-emf"),
        _eml( ".eml","message/rfc822" ),
        _ent( ".ent","text/xml"),
        _epi( ".epi","application/x-epi" ),
        _eps( ".eps","application/x-ps"),
        _eps2( ".eps","application/postscript" ),
        _etd( ".etd","application/x-ebx"),
        _exe( ".exe","application/x-msdownload" ),
        _fax( ".fax","image/fax"),
        _fdf( ".fdf","application/vnd.fdf" ),
        _fif( ".fif","application/fractals"),
        _fp( ".fo","text/xml" ),
        _frm( ".frm","application/x-frm"),
        _g4( ".g4","application/x-g4" ),
        _gbr( ".gbr","application/x-gbr"),
        _x( ".","application/x-" ),
        _gif( ".gif","image/gif"),
        _gl2( ".gl2","application/x-gl2" ),
        _gp4( ".gp4","application/x-gp4"),
        _hgl( ".hgl","application/x-hgl" ),
        _hmr( ".hmr","application/x-hmr"),
        _hpg( ".hpg","application/x-hpgl" ),
        _hpl( ".hpl","application/x-hpl"),
        _hqx( ".hqx","application/mac-binhex40" ),
        _hrf( ".hrf","application/x-hrf"),
        _hta( ".hta","application/hta" ),
        _htc( ".htc","text/x-component"),
        _htm( ".htm","text/html" ),
        _html( ".html","text/html"),
        _htt( ".htt","text/webviewhtml" ),
        _htx( ".htx","text/html"),
        _icb( ".icb","application/x-icb" ),
        _ico( ".ico","image/x-icon"),
        _ico2( ".ico","application/x-ico" ),
        _iff( ".iff","application/x-iff"),
        _ig4( ".ig4","application/x-g4" ),
        _igs( ".igs","application/x-igs"),
        _iii( ".iii","application/x-iphone" ),
        _img( ".img","application/x-img"),
        _ins( ".ins","application/x-internet-signup" ),
        _isp( ".isp","application/x-internet-signup"),
        _IVF( ".IVF","video/x-ivf" ),
        _jav( ".java","java/*"),
        _jfi( ".jfif","image/jpeg" ),
        _jpe( ".jpe","image/jpeg"),
        _jpex( ".jpe","application/x-jpe" ),
        _jpeg( ".jpeg","image/jpeg"),
        _jpg( ".jpg","image/jpeg" ),
        _jpgx( ".jpg","application/x-jpg"),
        _js( ".js","application/x-javascript" ),
        _jsp( ".jsp","text/html"),
        _la1( ".la1","audio/x-liquid-file" ),
        _lar( ".lar","application/x-laplayer-reg"),
        _lat( ".latex","application/x-latex" ),
        _lav( ".lavs","audio/x-liquid-secure"),
        _lbm( ".lbm","application/x-lbm" ),
        _lmsff( ".lmsff","audio/x-la-lms"),
        _ls( ".ls","application/x-javascript" ),
        _ltr( ".ltr","application/x-ltr"),
        _m1v( ".m1v","video/x-mpeg" ),
        _m2v( ".m2v","video/x-mpeg"),
        _m3u( ".m3u","audio/mpegurl" ),
        _m4e( ".m4e","video/mpeg4"),
        _mac( ".mac","application/x-mac" ),
        _man( ".man","application/x-troff-man"),
        _mat( ".math","text/xml" ),
        _mdb( ".mdb","application/msaccess"),
        _mdbx( ".mdb","application/x-mdb" ),
        _mfp( ".mfp","application/x-shockwave-flash"),
        _mht( ".mht","message/rfc822" ),
        _mhtml( ".mhtml","message/rfc822"),
        _mi( ".mi","application/x-mi" ),
        _mid( ".mid","audio/mid"),
        _midi( ".midi","audio/mid" ),
        _mil( ".mil","application/x-mil"),
        _mml( ".mml","text/xml" ),
        _mnd( ".mnd","audio/x-musicnet-download"),
        _mns( ".mns","audio/x-musicnet-stream" ),
        _moc( ".mocha","application/x-javascript"),
        _mov( ".movie","video/x-sgi-movie" ),
        _mp1( ".mp1","audio/mp1"),
        _mp2( ".mp2","audio/mp2" ),
        _mp2v( ".mp2v","video/mpeg"),
        _mp3( ".mp3","audio/mp3" ),
        _mp4( ".mp4","video/mpeg4"),
        _mpa( ".mpa","video/x-mpg" ),
        _mpd( ".mpd","application/vnd.ms-project"),
        _mpe( ".mpe","video/x-mpeg" ),
        _mpeg( ".mpeg","video/mpg"),
        _mpg( ".mpg","video/mpg" ),
        _mpga( ".mpga","audio/rn-mpeg"),
        _mpp( ".mpp","application/vnd.ms-project" ),
        _mps( ".mps","video/x-mpeg"),
        _mpt( ".mpt","application/vnd.ms-project" ),
        _mpv( ".mpv","video/mpg"),
        _mpv2( ".mpv2","video/mpeg" ),
        _mpw( ".mpw","application/vnd.ms-project"),
        _mpx( ".mpx","application/vnd.ms-project" ),
        _mtx( ".mtx","text/xml"),
        _mxp( ".mxp","application/x-mmxp" ),
        _net( ".net","image/pnetvue"),
        _nrf( ".nrf","application/x-nrf" ),
        _nws( ".nws","message/rfc822"),
        _odc( ".odc","text/x-ms-odc" ),
        _out( ".out","application/x-out"),
        _p10( ".p10","application/pkcs10" ),
        _p12( ".p12","application/x-pkcs12"),
        _p7b( ".p7b","application/x-pkcs7-certificates" ),
        _p7c( ".p7c","application/pkcs7-mime"),
        _p7m( ".p7m","application/pkcs7-mime" ),
        _p7r( ".p7r","application/x-pkcs7-certreqresp"),
        _p7s( ".p7s","application/pkcs7-signature" ),
        _pc5( ".pc5","application/x-pc5"),
        _pci( ".pci","application/x-pci" ),
        _pcl( ".pcl","application/x-pcl"),
        _pcx( ".pcx","application/x-pcx" ),
        _pdf( ".pdf","application/pdf"),
        _pdx( ".pdx","application/vnd.adobe.pdx"),
        _pfx( ".pfx","application/x-pkcs12" ),
        _pgl( ".pgl","application/x-pgl"),
        _pic( ".pic","application/x-pic" ),
        _pko( ".pko","application/vnd.ms-pki.pko"),
        _pl( ".pl","application/x-perl" ),
        _plg( ".plg","text/html"),
        _pls( ".pls","audio/scpls" ),
        _plt( ".plt","application/x-plt"),
        _png( ".png","image/png" ),
        _pngx( ".png","application/x-png"),
        _pot( ".pot","application/vnd.ms-powerpoint" ),
        _ppa( ".ppa","application/vnd.ms-powerpoint"),
        _ppm( ".ppm","application/x-ppm" ),
        _pps( ".pps","application/vnd.ms-powerpoint"),
        _ppt( ".ppt","application/vnd.ms-powerpoint" ),
        _pptx( ".ppt","application/x-ppt"),
        _pr( ".pr","application/x-pr" ),
        _prf( ".prf","application/pics-rules"),
        _prn( ".prn","application/x-prn" ),
        _prt( ".prt","application/x-prt"),
        _psx( ".ps","application/x-ps" ),
        _ps( ".ps","application/postscript"),
        _ptn( ".ptn","application/x-ptn" ),
        _pwz( ".pwz","application/vnd.ms-powerpoint"),
        _r3t( ".r3t","text/vnd.rn-realtext3d" ),
        _ra( ".ra","audio/vnd.rn-realaudio"),
        _ram( ".ram","audio/x-pn-realaudio" ),
        _ras( ".ras","application/x-ras"),
        _rat( ".rat","application/rat-file" ),
        _rdf( ".rdf","text/xml"),
        _rec( ".rec","application/vnd.rn-recording" ),
        _red( ".red","application/x-red"),
        _rgb( ".rgb","application/x-rgb" ),
        _rjs( ".rjs","application/vnd.rn-realsystem-rjs"),
        _rjt( ".rjt","application/vnd.rn-realsystem-rjt" ),
        _rlc( ".rlc","application/x-rlc"),
        _rle( ".rle","application/x-rle" ),
        _rm( ".rm","application/vnd.rn-realmedia"),
        _rmf( ".rmf","application/vnd.adobe.rmf" ),
        _rmi( ".rmi","audio/mid"),
        _rmj( ".rmj","application/vnd.rn-realsystem-rmj" ),
        _rmm( ".rmm","audio/x-pn-realaudio"),
        _rmp( ".rmp","application/vnd.rn-rn_music_package" ),
        _rms( ".rms","application/vnd.rn-realmedia-secure"),
        _rmv( ".rmvb","application/vnd.rn-realmedia-vbr" ),
        _rmx( ".rmx","application/vnd.rn-realsystem-rmx"),
        _rnx( ".rnx","application/vnd.rn-realplayer" ),
        _rp( ".rp","image/vnd.rn-realpix"),
        _rpm( ".rpm","audio/x-pn-realaudio-plugin" ),
        _rsm( ".rsml","application/vnd.rn-rsml"),
        _rt( ".rt","text/vnd.rn-realtext" ),
        _rtf( ".rtf","application/msword"),
        _rtfx( ".rtf","application/x-rtf" ),
        _rv( ".rv","video/vnd.rn-realvideo"),
        _sam( ".sam","application/x-sam" ),
        _sat( ".sat","application/x-sat"),
        _sdp( ".sdp","application/sdp" ),
        _sdw( ".sdw","application/x-sdw"),
        _sit( ".sit","application/x-stuffit" ),
        _slb( ".slb","application/x-slb"),
        _sld( ".sld","application/x-sld" ),
        _slk( ".slk","drawing/x-slk"),
        _smi( ".smi","application/smil" ),
        _smil( ".smil","application/smil"),
        _smk( ".smk","application/x-smk" ),
        _snd( ".snd","audio/basic"),
        _sol( ".sol","text/plain" ),
        _sor( ".sor","text/plain"),
        _spc( ".spc","application/x-pkcs7-certificates" ),
        _spl( ".spl","application/futuresplash"),
        _spp( ".spp","text/xml" ),
        _ssm( ".ssm","application/streamingmedia"),
        _sst( ".sst","application/vnd.ms-pki.certstore" ),
        _stl( ".stl","application/vnd.ms-pki.stl"),
        _stm( ".stm","text/html" ),
        _sty( ".sty","application/x-sty"),
        _svg( ".svg","text/xml" ),
        _swf( ".swf","application/x-shockwave-flash"),
        _tdf( ".tdf","application/x-tdf" ),
        _tg4( ".tg4","application/x-tg4"),
        _tga( ".tga","application/x-tga" ),
        _tif( ".tif","image/tiff"),
        _tifx( ".tif","application/x-tif" ),
        _tiff( ".tiff","image/tiff"),
        _tld( ".tld","text/xml" ),
        _top( ".top","drawing/x-top"),
        _tor( ".torrent","application/x-bittorrent" ),
        _tsd( ".tsd","text/xml"),
        _txt( ".txt","text/plain" ),
        _uin( ".uin","application/x-icq"),
        _uls( ".uls","text/iuls" ),
        _vcf( ".vcf","text/x-vcard"),
        _vda( ".vda","application/x-vda" ),
        _vdx( ".vdx","application/vnd.visio"),
        _vml( ".vml","text/xml" ),
        _vpg( ".vpg","application/x-vpeg005"),
        _vsd( ".vsd","application/vnd.visio" ),
        _vsdx( ".vsd","application/x-vsd"),
        _vss( ".vss","application/vnd.visio" ),
        _vst( ".vst","application/vnd.visio"),
        _vstx( ".vst","application/x-vst" ),
        _vsw( ".vsw","application/vnd.visio"),
        _vsx( ".vsx","application/vnd.visio" ),
        _vtx( ".vtx","application/vnd.visio"),
        _vxm( ".vxml","text/xml" ),
        _wav( ".wav","audio/wav"),
        _wax( ".wax","audio/x-ms-wax" ),
        _wb1( ".wb1","application/x-wb1"),
        _wb2( ".wb2","application/x-wb2" ),
        _wb3( ".wb3","application/x-wb3"),
        _wbm( ".wbmp","image/vnd.wap.wbmp" ),
        _wiz( ".wiz","application/msword"),
        _wk3( ".wk3","application/x-wk3" ),
        _wk4( ".wk4","application/x-wk4"),
        _wkq( ".wkq","application/x-wkq" ),
        _wks( ".wks","application/x-wks"),
        _wm( ".wm","video/x-ms-wm" ),
        _wma( ".wma","audio/x-ms-wma"),
        _wmd( ".wmd","application/x-ms-wmd" ),
        _wmf( ".wmf","application/x-wmf"),
        _wml( ".wml","text/vnd.wap.wml" ),
        _wmv( ".wmv","video/x-ms-wmv"),
        _wmx( ".wmx","video/x-ms-wmx" ),
        _wmz( ".wmz","application/x-ms-wmz"),
        _wp6( ".wp6","application/x-wp6" ),
        _wpd( ".wpd","application/x-wpd"),
        _wpg( ".wpg","application/x-wpg" ),
        _wpl( ".wpl","application/vnd.ms-wpl"),
        _wq1( ".wq1","application/x-wq1" ),
        _wr1( ".wr1","application/x-wr1"),
        _wri( ".wri","application/x-wri" ),
        _wrk( ".wrk","application/x-wrk"),
        _ws( ".ws","application/x-ws" ),
        _ws2( ".ws2","application/x-ws"),
        _wsc( ".wsc","text/scriptlet" ),
        _wsd( ".wsdl","text/xml"),
        _wvx( ".wvx","video/x-ms-wvx" ),
        _xdp( ".xdp","application/vnd.adobe.xdp"),
        _xdr( ".xdr","text/xml" ),
        _xfd( ".xfd","application/vnd.adobe.xfd"),
        _xfdf( ".xfdf","application/vnd.adobe.xfdf" ),
        _xht( ".xhtml","text/html"),
        _xls( ".xls","application/vnd.ms-excel" ),
        _xls_x( ".xls","application/x-xls"),
        _xlw( ".xlw","application/x-xlw" ),
        _xml( ".xml","text/xml"),
        _xpl( ".xpl","audio/scpls" ),
        _xq( ".xq","text/xml"),
        _xql( ".xql","text/xml" ),
        _xqu( ".xquery","text/xml"),
        _xsd( ".xsd","text/xml" ),
        _xsl( ".xsl","text/xml"),
        _xslt( ".xslt","text/xml" ),
        _xwd( ".xwd","application/x-xwd"),
        _x_b( ".x_b","application/x-x_b"),
        _sis( ".sis","application/vnd.symbian.install"),
        _sisx( ".sisx","application/vnd.symbian.install"),
        _x_t( ".x_t","application/x-x_t"),
        _ipa( ".ipa","application/vnd.iphone"),
        _apk( ".apk","application/vnd.android.package-archive"),
        _xap( ".xap","application/x-silverlight-app"),
    ;

    ResponseFileStreamContentType(String suffix, String expression) {
        this.suffix = suffix;
        this.expression = expression;
    }

    private String suffix;

    private String expression;


    public String getSuffix() {
        return suffix;
    }

    public String getExpression() {
        return expression;
    }

    /**
     * 根据后缀获取contentType
     * @param suffix
     * @return
     */
    public static Set<ResponseFileStreamContentType> getContentTypesBySuffix(String suffix) {
        ResponseFileStreamContentType[] responseFileStreamContentTypes = ResponseFileStreamContentType.values();
        return Arrays.stream(responseFileStreamContentTypes).filter(e -> e.getSuffix().equals(suffix)).collect(Collectors.toSet());
    }

    /**
     * 根据文件名获取contentType
     * @param fileName
     * @return
    */
    public static Set<ResponseFileStreamContentType> getContentTypesByFileName(String fileName) {
        String suffix = StringUtil.subFrontByLastIndexOf(fileName, ".");
        return getContentTypesBySuffix(suffix);
    }

}