import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 策略枚举
 */
public enum ImportDataTypeEnum {
    STRING("string"){
        @Override
        public Object getExcelValue(String cell, String fieldName){
            return null;
        }
    },
    DATE("date"){
        @Override
        public Object getExcelValue(String cell, String fieldName){
            return null;
        }
    },
    NUMBER("number"){
        @Override
        public Object getExcelValue(String cell, String fieldName){
            return null;
        }
    };

    private static Map<String, List<ImportDataTypeEnum>> map = Arrays.stream(ImportDataTypeEnum.values())
            .collect(Collectors.groupingBy(ImportDataTypeEnum::getDataType));

    private final String dataType;

    ImportDataTypeEnum(String dataType){
        this.dataType = dataType;
    }

    public String getDataType() {
        return dataType;
    }

    public abstract Object getExcelValue(String cell, String fieldName) throws Exception;

    public static ImportDataTypeEnum getImportDataTypeEnum(String dataType) {
//        for (ImportDataTypeEnum ele : ImportDataTypeEnum.values()) {
//            if (ele.getDataType().equals(dataType)) return ele;
//        }
        return null == map.get(dataType) ? ImportDataTypeEnum.STRING : map.get(dataType).get(0);
    }
}
