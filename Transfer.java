package interview;

////评测题目: 金额转换,将小写金额转换成大写,精确到亿
//
//要求：
//
//        1. 代码尽量优雅，可运行
//        2. 实现题目要求，注意边界处理
//
//        示例：
//        输入：1098.87
//        输出：壹仟零玖拾捌圆捌角柒分
//        输入：89
//        输出：捌拾玖圆整


public class Transfer {
    private static final String[] NUMBERS = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    private static final char[] IUNIT = new char[]{'圆', '拾', '佰', '仟', '万', '拾', '佰', '仟', '亿', '拾', '佰', '仟', '万', '拾', '佰', '仟'};
    private static final char[] DUNIT = new char[]{'角', '分', '厘'};

    public static String transfer(Double val) {
        StringBuffer sbf = new StringBuffer();

        int iVal = val.intValue();
        int preNum = 0;
        if (iVal == 0) {
            sbf.insert(0, "零圆");
        } else {
            for (int i = 0; iVal != 0; i++) {
                int num = iVal % 10;
                if (num == 0) { //等于零的时候
                    if (i % 4 == 0) {
                        sbf.insert(0, IUNIT[i]);
                    } else if (preNum != 0) {
                        sbf.insert(0, NUMBERS[num]);
                    }
                } else {
                    sbf.insert(0, IUNIT[i]);
                    sbf.insert(0, NUMBERS[num]);
                }
                preNum = num;
                iVal /= 10;
            }
        }
        String dVal = String.format("%.3f", val - val.intValue());
        for (int i = 2; i < dVal.length(); i++) {
            int v = Integer.parseInt(dVal.substring(i,i+1));
            sbf.append(NUMBERS[v]);
            sbf.append(DUNIT[i-2]);
        }
        return sbf.toString();
    }

    public static void main(String[] args) {
        System.out.println(transfer(20300098.876));
        System.out.println(transfer(09.876));
        System.out.println(transfer(109.876));
    }
}
