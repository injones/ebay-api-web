package com.example.demo.service;

import com.ebay.services.client.ClientConfig;
import com.ebay.services.client.FindingServiceClientFactory;
import com.ebay.services.finding.*;
import com.example.demo.Form;
import com.example.demo.dto.EbayItem;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by JonesIN on 20/01/2018.
 */
@Service
public class EbayService implements IEbayService {

    @Override
    public List<EbayItem> search(Form form) {
        ClientConfig config = new ClientConfig();
        config.setEndPointAddress("http://svcs.sandbox.ebay.com/services/search/FindingService/v1");
        config.setApplicationId("<AppID>");
        config.setGlobalId("EBAY-GB");
//        config.setEndPointAddress("http://svcs.ebay.com/services/search/FindingService/v1");
//        config.setApplicationId("<AppID>");
        FindingServicePortType serviceClient = FindingServiceClientFactory.getServiceClient(config);
        FindItemsAdvancedRequest request = new FindItemsAdvancedRequest();
        request.getOutputSelector().add(OutputSelectorType.SELLER_INFO);
        request.getOutputSelector().add(OutputSelectorType.ASPECT_HISTOGRAM);
        request.getOutputSelector().add(OutputSelectorType.CATEGORY_HISTOGRAM);
        request.getCategoryId().add(form.getCategoryId());
        request.setKeywords(form.getKeyword());
        form.getFilters().stream().map(f -> {
            ItemFilter filter = new ItemFilter();
            filter.setName(ItemFilterType.fromValue(f.getType().getValue()));
            if (f.getParamName() != null){
                filter.setParamName(f.getParamName());
                filter.setParamValue(f.getParamVal());
            }
            filter.getValue().add(f.getValue());
            return filter;
        }).forEach(f -> request.getItemFilter().add(f));
//        ItemFilter filter = new ItemFilter();
//        filter.setName(ItemFilterType.FEEDBACK_SCORE_MIN);
//        filter.getValue().add("99");
//        request.getItemFilter().add(filter);
////        filter = new ItemFilter();
////        filter.setName(ItemFilterType.TOP_RATED_SELLER_ONLY);
////        filter.getValue().add("true");
////        request.getItemFilter().add(filter);
//        filter = new ItemFilter();
//        filter.setName(ItemFilterType.MAX_PRICE);
//        filter.setParamName("Currency");
//        filter.setParamValue("GBP");
//        filter.getValue().add("50.0");
//        request.getItemFilter().add(filter);
        request.setSortOrder(SortOrderType.END_TIME_SOONEST);
        PaginationInput pi = new PaginationInput();
        pi.setEntriesPerPage(5);
        request.setPaginationInput(pi);
        FindItemsAdvancedResponse response = serviceClient.findItemsAdvanced(request);
        if (response != null && response.getSearchResult() != null){
            List<SearchItem> items = response.getSearchResult().getItem();
            response.getSearchResult().getItem().forEach(i -> System.out.println(i.getGalleryURL()));
            System.out.println(response.getAspectHistogramContainer().getAspect().get(0).getName() + ", " +
                    response.getAspectHistogramContainer().getAspect().get(0).getValueHistogram());
            List<SearchItem> reslt = items
                    .stream()
                    .filter(i -> i.getListingInfo()
                            .getStartTime()
                            .after(LocalDateTime.now().minus(30, ChronoUnit.MINUTES)))
                    .collect(Collectors.toList());
//            System.out.println("HERE");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
            items.forEach(i -> System.out.println(i.getSellingStatus().getConvertedCurrentPrice().getValue()));
//            items.stream()
//                    .filter(i -> !i.getSellerInfo().getSellerUserName().contains("testuser"))
//                    .collect(Collectors.toList())
//                    .forEach(System.out::println);
            return items.stream()
                    .map(i -> new EbayItem(i.getTitle(), i.getViewItemURL(),
                            sdf.format(i.getListingInfo().getStartTime().getTime()),
                                    sdf.format(i.getListingInfo().getEndTime().getTime()),
                                            i.getSellingStatus().getConvertedCurrentPrice().getValue(),
                            i.getGalleryURL()))
                    .collect(Collectors.toList());
        }

        return null;
    }
}
