import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 策略枚举
 */
public enum TestStrategyEnum {
    STRING{
        @Override
        public String getExcelValue(){
            return "getExcelValue:STRING";
        }
    },
    DATE{
        @Override
        public String getExcelValue(){
            return "getExcelValue:DATE";
        }
    },
    NUMBER{
        @Override
        public String getExcelValue(){
            return "getExcelValue:NUMBER";
        }
    };

    public abstract Object getExcelValue() throws Exception;
}