package thread;

public class RiskMock1 {
    public RiskMock1() {
    }

    public Long calculateQty(RiskVO riskVO) {
        System.out.println(riskVO.getAcctId());
        return 10L;
    }

}
