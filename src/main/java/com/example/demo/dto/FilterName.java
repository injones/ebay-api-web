package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

import javax.xml.bind.annotation.XmlEnumValue;

/**
 * Created by JonesIN on 26/01/2018.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum FilterName {
    CONDITION("Condition"),
    CURRENCY("Currency"),
    END_TIME_FROM("EndTimeFrom"),
    MOD_TIME_FROM("ModTimeFrom"),
    END_TIME_TO("EndTimeTo"),
    EXCLUDE_AUTO_PAY("ExcludeAutoPay"),
    BEST_OFFER_ONLY("BestOfferOnly"),
    FEATURED_ONLY("FeaturedOnly"),
    FEEDBACK_SCORE_MAX("FeedbackScoreMax"),
    FEEDBACK_SCORE_MIN("FeedbackScoreMin"),
    FREE_SHIPPING_ONLY("FreeShippingOnly"),
    GET_IT_FAST_ONLY("GetItFastOnly"),
    HIDE_DUPLICATE_ITEMS("HideDuplicateItems"),
    AVAILABLE_TO("AvailableTo"),
    LOCATED_IN("LocatedIn"),
    LOCAL_PICKUP_ONLY("LocalPickupOnly"),
    LOCAL_SEARCH_ONLY("LocalSearchOnly"),
    LISTING_TYPE("ListingType"),
    LOTS_ONLY("LotsOnly"),
    MAX_BIDS("MaxBids"),
    MIN_BIDS("MinBids"),
    MAX_PRICE("MaxPrice"),
    MIN_PRICE("MinPrice"),
    PAYMENT_METHOD("PaymentMethod"),
    MAX_QUANTITY("MaxQuantity"),
    MIN_QUANTITY("MinQuantity"),
    SELLER("Seller"),
    EXCLUDE_SELLER("ExcludeSeller"),
    EXCLUDE_CATEGORY("ExcludeCategory"),
    WORLD_OF_GOOD_ONLY("WorldOfGoodOnly"),
    MAX_DISTANCE("MaxDistance"),
    SELLER_BUSINESS_TYPE("SellerBusinessType"),
    TOP_RATED_SELLER_ONLY("TopRatedSellerOnly"),
    SOLD_ITEMS_ONLY("SoldItemsOnly"),
    CHARITY_ONLY("CharityOnly"),
    LISTED_IN("ListedIn"),
    EXPEDITED_SHIPPING_TYPE("ExpeditedShippingType"),
    MAX_HANDLING_TIME("MaxHandlingTime"),
    RETURNS_ACCEPTED_ONLY("ReturnsAcceptedOnly"),
    VALUE_BOX_INVENTORY("ValueBoxInventory");

    private final String value;

    FilterName(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
