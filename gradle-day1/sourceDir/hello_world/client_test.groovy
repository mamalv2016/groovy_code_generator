import com.lvmama.lvtraffic.promotion.common.dto.request.PromActivityCalculateRequest;
import com.lvmama.lvf.common.dto.*; 
import com.lvmama.lvtraffic.promotion.common.dto.promotion.*;
import com.lvmama.lvtraffic.promotion.common.client.*;

def req =new PromActivityCalculateRequest();
req.categoryType='LVFLIGHT'
req.bookingSource='PC'
req.totalOrderAmount=1300
req.departCity='上海'
req.arriveCity='北京'
req.airlineCode='CZ'
req.adultAmount=600
req.adultCount=2
req.childAmount=400
req.childCount=1
visitTime=1497413410000

PromotionActivityApiClient client = new  PromotionActivityApiClient();
BaseResultDto<PromotionActivityDto> result = client.getPromActivityWithAmount(req)
println result