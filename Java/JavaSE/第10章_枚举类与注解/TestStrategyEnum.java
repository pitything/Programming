package Java.JavaSE.第10章_枚举类与注解;

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