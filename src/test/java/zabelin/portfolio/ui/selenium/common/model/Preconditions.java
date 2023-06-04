package zabelin.portfolio.ui.selenium.common.model;

import java.util.Arrays;
import java.util.List;

public class Preconditions {

    public static class WMSite {

        public static class User {
            public static final String NEW_USER = "new user";
            public static final String RANDOM_USER = "random user";
            public static final String BASIC_USER = "basic user";
            public static final String EMPTY_USER = "empty user";
            public static final String CHANGE_DATA_USER = "change data user";
            public static final String LISTING_OWNER = "listing owner user";
            public static final String TRASH_LISTING_OWNER = "trash listing owner user";
            public static final String BRAND_OWNER = "brand owner user";
            public static final String OPS_MANAGER = "ops manager user";
            public static final String TAXES_ADMIN = "taxes user";
            public static final String MODERATOR = "moderator user";
            public static final String ONBOARDING_ADMIN = "onboarding admin user";
            public static final String SALES_MANAGER = "sales manager user";
            public static final String ORGANIZATION_ADMIN = "organization admin user";
            public static final String ORGANIZATION_MANAGER = "organization manager user";
            public static final String ORDERING = "ordering user";
            public static final String NONE = "none user";
        }

        public static class Page {
            // front pages
            public static final String HOME = "home page";
            public static final String MAPS = "maps page";
            public static final String BRANDS = "brands page";
            public static final String PRODUCTS = "products page";
            public static final String DEALS = "deals page";
            public static final String SALE = "sale page";
            public static final String LOGIN_PAGE = "login page";
            public static final String SERP = "serp page";
            public static final String STRAINS_FRONT = "strains_page";
            public static final String ORDERS = "orders";
            public static final String SHOP = "shop";
            public static final String DISPENSARIES = "dispensaries page";
            public static final String ADD_BUSINESS = "add_business_page";
            public static final String FILLED_DISPENSARY = "filled_dispensary";
            public static final String NON_FILLED_DISPENSARY = "non_filled_dispensary";
            public static final String FILLED_DELIVERY = "filled_delivery";
            public static final String FILLED_BRAND = "filled_brand";
            public static final String NON_FILLED_BRAND = "non_filled_brand";
            public static final String FILLED_MENU_DETAILS = "filled_menu_details";
            public static final String NON_FILLED_MENU_DETAILS = "non_filled_menu_details";
            public static final String FILLED_PRODUCT_DETAILS = "filled_product_details";
            public static final String NON_FILLED_PRODUCT_DETAILS = "non_filled_product_details";

            // admin pages
            public static final String NEW_ADMIN = "new_admin page";
            public static final String DEALS_ADMIN_REGIONS = "deals_regions page";
            public static final String DEALS_ADMIN_PLACEMENT = "deals_placement page";
            public static final String DEALS_ADMIN_MODERATION = "deals_moderation page";
            public static final String PROMO_CODES = "promo_codes page";
            public static final String ADMIN_DISPENSARIES = "admin_dispensaries page";
            public static final String ADMIN_DELIVERIES = "admin_deliveries page";
            public static final String STRAINS_ADMIN = "new_admin_strains_page";
            public static final String ADMIN_DASHBOARD = "admin_dashboard_page";
            public static final String WM_STORE = "wm_store";
            public static final String ADVERTISING_REPORTS = "new_admin_advertising_reports";
            public static final String AD_CAMPAIGNS = "admin_campaigns page";
            public static final String ORGANIZATIONS = "new_admin_organizations page";
            public static final String TEAM = "admin_team page";
            public static final String LISTING_PLACEMENTS = "admin_listing_placements page";
            public static final String CREATIVES_PAGE = "admin_creatives page";
            public static final String CREATIVES_DESIGNER_PAGE = "admin_creatives_designer page";
            public static final String MANAGE_REGIONS_PAGE = "manage_regions page";
        }

        public static class Locations {
            public static final String BASE_LOCATION = "base_location";
            public static final String WITH_DEALS = "with_deals";
            public static final String FILLED_LOCATION = "filled_location";
            public static final String NON_LEGAL_STATE = "non_legal_state";
            public static final String WITH_ADS = "with_ads";
        }

        public static class FeatureFlags {
            public static final String LIMIT_REVIEW_FREQUENCY = "limit_review_frequency";
            public static final String TAX_ADMIN = "tax_admin";
            public static final String ORDERS_LANDING = "orders_landing";
            public static final String ORDERS_LANDING_NAV = "orders_landing_nav";
            public static final String ORDERS_SHOP = "orders_shop";
            public static final String MOST_RECENT_ORDER_LISTING = "most_recent_order_listing";
            public static final String RECENT_ORDER_PRODUCTS = "recent_order_products";
            public static final String PREVENT_CHECKOUT_WHEN_LISTING_CLOSED = "prevent_checkout_when_listing_closed";
            public static final String PICKUP_PREFERENCE = "pickup_preference";
            public static final String SALE_PRICING = "sale_pricing";
            public static final String SHOP_V2_MENU_LINKS_NO_FILTERS = "shop_v2_menu_links_no_filters";
            public static final String MERGED_DESKTOP_LOCATION_BUTTONS = "merged_desktop_location_buttons";
            public static final String ALLOW_PICKUP_AFTERHOURS = "allow_pickup_afterhours";
            public static final String CART_PROMO_CODE_BANNER = "cart_promo_code_banner";
            public static final String RANDOMIZE_RETAILERS = "randomize_retailers";
            public static final String ONE_PAGE_CHECKOUT = "one_page_checkout";
            public static final String GUEST_CHECKOUT = "guest_checkout";
            public static final String ALLOW_GUEST_CHECKOUT = "allow_guest_checkout";
            public static final String ID_CART_AS_MEDICAL = "id_cart_as_medical";
            public static final String CART_CONFLICT_INITIATIVE = "cart_conflict_initiative";
            public static final String DEALS_SKIP_MODERATION = "deals_skip_moderation";
            public static final String REACT_ADMIN_HIDE_LIMITATIONS = "react_admin_hide_limitations";
            public static final String LINE_ITEM_DISCOUNTS = "line_item_discounts";
            public static final String NEW_CART_IMPLEMENTATION = "new-cart-implementation";
            public static final String DISALLOW_THIRD_PARTY_MARKETPLACE = "disallow_third_party_marketplace";
            public static final String HIDE_MEDREC_IMAGE = "hide_medrec_image";
            public static final String SAVINGS_PAGE_UPDATE = "savings_page_update";
            public static final String SHOW_TAX_TOTAL = "show_tax_total";
            public static final String SCHEDULED_ORDERS = "scheduled_orders";
            public static final String PHOTO_ID_VERIFICATION = "photo_id_verification";
            public static final String CART_LIMIT_CA = "CART_LIMIT_CA";

            public static List<String> optimizelyWDCFlags = Arrays.asList(
                    ORDERS_LANDING, ORDERS_LANDING_NAV, ORDERS_SHOP, MOST_RECENT_ORDER_LISTING,
                    RECENT_ORDER_PRODUCTS, PREVENT_CHECKOUT_WHEN_LISTING_CLOSED, PICKUP_PREFERENCE, SALE_PRICING,
                    SHOP_V2_MENU_LINKS_NO_FILTERS, MERGED_DESKTOP_LOCATION_BUTTONS, ALLOW_PICKUP_AFTERHOURS,
                    CART_PROMO_CODE_BANNER, RANDOMIZE_RETAILERS, ONE_PAGE_CHECKOUT, GUEST_CHECKOUT, ID_CART_AS_MEDICAL,
                    CART_CONFLICT_INITIATIVE, REACT_ADMIN_HIDE_LIMITATIONS, LINE_ITEM_DISCOUNTS,
                    DISALLOW_THIRD_PARTY_MARKETPLACE, HIDE_MEDREC_IMAGE, SAVINGS_PAGE_UPDATE, SHOW_TAX_TOTAL,
                    SCHEDULED_ORDERS, PHOTO_ID_VERIFICATION);
            public static List<String> optimizelyOrdersServiceFlags = Arrays.asList(
                    NEW_CART_IMPLEMENTATION, CART_LIMIT_CA);
            public static List<String> flipperFlags = Arrays.asList(
                    LIMIT_REVIEW_FREQUENCY, TAX_ADMIN, DEALS_SKIP_MODERATION, ALLOW_GUEST_CHECKOUT);
        }
    }

    public static class Logistics {

        public static class User {
            public static final String OPS_MANAGER_USER = "OpsManager";
            public static final String MULTI_ORG_OWNER_USER = "MultiOrgOwnerUser";
            public static final String MULTI_ORG_ADMIN_USER = "MultiOrgAdminUser";
            public static final String MULTI_ORG_DISPATCHER_USER = "MultiOrgDispatcherUser";
            public static final String ORG_MULTI_PICKUP_ADMIN_USER = "OrgMultiPickupAdminUser";
            public static final String ORG_MULTI_PICKUP_DISPATCHER_USER = "OrgMultiPickupDispatcherUser";
            public static final String ORG_SINGLE_PICKUP_OWNER_USER = "OrgSinglePickupOwnerUser";
            public static final String ORG_SINGLE_DELIVERY_OWNER_USER = "OrgSingleDeliveryOwnerUser";
            public static final String ORG_SINGLE_DELIVERY_ADMIN_USER = "OrgSingleDeliveryAdminUser";
            public static final String ORG_SINGLE_DELIVERY_DRIVER_USER = "OrgSingleDeliveryDriverUser";
        }

        public static class Page {
            public static final String ALL_ORDERS_PAGE = "all orders page";
            public static final String DELIVERY_PAGE = "delivery page";
            public static final String FULFILLMENT_PAGE = "fulfillment page";
            public static final String ORDERS_PROMO_CODES = "orders promo_codes page";
        }
    }

    public static class WMStore {

        public static class User {
            public static final String BASE_USER = "base user";
            public static final String STORE_OWNER = "store owner user";
            public static final String BRAND_OWNER = "brand store owner user";
        }
    }

    public static class API {
        public static class User {
            public static final String BASIC_USER = "basic user";
            public static final String CHANGE_DATA_USER = "change data user";
            public static final String OPS_MANAGER = "ops manager";
            public static final String LISTING_OWNER = "listing owner";
            public static final String SALES_MANAGER = "sales manager";
            public static final String BRAND_OWNER = "brand owner";
            public static final String NEW_USER = "new user";
            public static final String NEW_VERIFIED_USER = "new verified user";

            // commerce
            public static final String COMMERCE_API_USER = "commerce api user";

            // gql
            public static final String OOS_API_USER = "oos api user";
        }

        public static class Headers {
            public static final String EMPTY = "Empty";
            public static final String BASIC = "Basic";
            public static final String VND_API = "Vnd Api";
        }
    }
}

