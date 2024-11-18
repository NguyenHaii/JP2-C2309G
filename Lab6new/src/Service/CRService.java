package Service;

import Entity.CRIndex;
import Entity.CRStatics;
import Entity.StaticsView;
import General.IGeneral;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CRService implements IGeneral<CRStatics> {
    private final List<StaticsView> staticsViewList;

    public CRService(List<StaticsView> staticsViewList) {
        this.staticsViewList = staticsViewList;
    }

    @Override
    public Map<CRStatics, CRStatics> dataCRS() {
        return staticsViewList.stream()
                .collect(Collectors.groupingBy(
                        cr -> new CRStatics(cr.getId(), cr.getMonthOfDate(), cr.getYearOfDate()),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                this::aggregateCRStatics
                        )
                ));
    }

    private CRStatics aggregateCRStatics(List<StaticsView> staticsViews) {
        var first = staticsViews.get(0); // Lấy phần tử đầu tiên làm tham chiếu
        return new CRStatics(
                first.getId(),
                first.getMonthOfDate(),
                first.getYearOfDate(),
                staticsViews.stream().mapToInt(StaticsView::getView).sum(),
                staticsViews.stream().mapToInt(StaticsView::getAddToCart).sum(),
                staticsViews.stream().mapToInt(StaticsView::getCheckOut).sum()
        );
    }

    public CRIndex calculateCRIndex(CRStatics crStatics) {
        var totalView = crStatics.getTotalView();
        var totalAddToCart = crStatics.getTotalAddToCart();
        var totalCheckOut = crStatics.getTotalCheckOut();

        // Tính toán các chỉ số CR
        var crViews = (totalView > 0) ? (double) totalView / totalView * 100 : 0;
        var crAddToCart = (totalView > 0) ? (double) totalAddToCart / totalView * 100 : 0;
        var crCheckOut = (totalView > 0) ? (double) totalCheckOut / totalView * 100 : 0;


        return new CRIndex(
                crStatics.getId(),
                crStatics.getMonth(),
                crStatics.getYear(),
                crViews,
                crAddToCart,
                crCheckOut
        );
    }

}
