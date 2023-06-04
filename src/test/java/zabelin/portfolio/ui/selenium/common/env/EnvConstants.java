/**
 * Copyright (c) 2016-2017 Severotek, LLC.
 * All Rights Reserved.
 * <p>
 * This software is the confidential and proprietary information of
 * Severotek, LLC. ("Confidential Information").
 * <p>
 * You shall not disclose such Confidential Information and shall use it
 * only in accordance with the terms of the license agreement you entered
 * into with Severotek.
 * <p>
 * Copyright Version 1.0
 */
package zabelin.portfolio.ui.selenium.common.env;

import org.testng.annotations.Test;
import zabelin.portfolio.core.common.Log;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EnvConstants extends zabelin.portfolio.core.common.EnvConstants {

    protected static final String mpropkey = System.getProperty("mpropkey");

    //URLs
    public static final String PRODUCTION_BASE_PAGE = "https://weedmaps.com/";
    public static final String BASE_PAGE = getProperty("base.page");
    public static final String BASE_LOCATION = getProperty("base.location");
    public static final String HOME_PAGE = BASE_PAGE + BASE_LOCATION;
    public static final String LOGIN_PAGE = BASE_PAGE + "login";
    public static final String BASE_EXCHANGE_PAGE = getProperty("base.exchange.page");
    public static final String BASE_CRM_PAGE = getProperty("crm.page");
    public static final String OPTIN_URL_API_V3 = getProperty("optin.url.api.v3");
    public static final String CRM_SPROUT_ONLINE = getProperty("crm.sprout.online");
    public static final String QA_SPROUT_CRM_API = getProperty("qa.sprout.crm.api");
    public static final String QA_SPROUT_WALLET_API = getProperty("qa.sprout.wallet.api");
    public static final String QA_MY_LOYALTY_WALLET_APP = getProperty("qa.my.loyalty.wallet.app");
    public static final String LOYALTY_WALLET_API = getProperty("loyalty.wallet.api");
    public static final String GATEWAYQA_WEBSITE_WIDGET = getProperty("gatewayqa.website.widget");
    public static final String QA_SPROUT_MESSENGER_API = getProperty("qa.sprout.messenger.api");
    public static final String BASE_IMAGE_EXCHANGE_PAGE = getProperty("base.image.exchange.page");
    public static final String NEWADMIN_PAGE = BASE_PAGE + "new_admin";
    public static final String NEWADMIN_SPECIFIC_FF_ON_OFF_POINT = NEWADMIN_PAGE + "/flipper_api/features/%s/boolean";
    public static final String ADMIN_PAGE = getProperty("admin.page");
    public static final String TOOLS_PAGE = getProperty("tool.page");
    public static final String CART_PAGE = BASE_PAGE + "cart";
    public static final String RETAIL_PAGE = getProperty("retail.page");
    public static final String EMBEDS_PAGE = getProperty("embeds.page");
    public static final String BEAM_OPA_PAGE = getProperty("api.opa.beam");
    public static final String POLICIES_ADMIN_PAGE = getProperty("beam.policies.admin.page");
    public static final String LOGISTIC_PAGE = getProperty("logistic.page");
    public static final String LOGISTIC_SWITCH_ORG_PAGE = LOGISTIC_PAGE + "ops-org-selection";
    public static final String LOGISTIC_FULFILLMENT_PAGE = LOGISTIC_PAGE + "fulfillment";
    public static final String LOGISTIC_DELIVERY_PAGE = LOGISTIC_PAGE + "delivery";
    public static final String LOGISTIC_ORDERS_PAGE = LOGISTIC_PAGE + "orders";
    public static final String LOGISTIC_LISTINGS_PAGE = LOGISTIC_PAGE + "listings";
    public static final String LOGISTIC_ZONES = LOGISTIC_PAGE + "zones";
    public static final String LOGISTIC_DRIVERS = LOGISTIC_PAGE + "drivers";
    public static final String LOGISTIC_VEHICLES = LOGISTIC_PAGE + "vehicles";
    public static final String LOGISTIC_DRIVERS_VEHICLES_NEW = LOGISTIC_VEHICLES + "/new";
    public static final String LOGISTIC_DRIVERS_VEHICLES_EDIT = LOGISTIC_VEHICLES + "/%s/edit";
    public static final String LOGISTIC_SETTINGS_NOTIFICATIONS = LOGISTIC_PAGE + "settings/notifications";
    public static final String LOGISTIC_ORDER_DETAIL_PAGE = LOGISTIC_PAGE + "orders/%s";
    public static final String LOGISTIC_CREATE_ORDER_PAGE = LOGISTIC_PAGE + "orders/new";
    public static final String LOGISTIC_PICKUP_PAGE = LOGISTIC_PAGE + "pickup";
    public static final String LOGISTIC_PEOPLE_PAGE = LOGISTIC_PAGE + "people";
    //    public static final String LOGISTIC_DISTRIBUTION_CENTERS = LOGISTIC_PAGE + "distribution-centers";
    public static final String OPA_DATASOURCE_ID_FOR_SUBSCRIPTIONS_TEST = getProperty("opa.datasource.id.for.subscriptions.test");
    public static final String OPA_DATASERVICE_ID_FOR_SUBSCRIPTIONS_TEST = getProperty("opa.dataservice.id.for.subscriptions.test");
    public static final String OPA_DATASOURCE_ID_FOR_SVC3 = getProperty("opa.datasource.id.for.svc3");
    public static final String LOGISTIC_DISCOUNTS = LOGISTIC_PAGE + "promotions";
    public static final String LOGISTIC_NEW_DISCOUNT = LOGISTIC_DISCOUNTS + "/new";

    //optimizely API
    public static final String OPTIMIZELY_URL_PREFIX = "https://api.optimizely.com/v2/features";
    public static final String OPTIMIZELY_WDC_DATAFILE_JSON = getProperty("optimizely.wdc.datafile.json");
    public static final String OPTIMIZELY_ORDERS_SERVICE_DATAFILE_JSON = getProperty("optimizely.orders.service.datafile.json");
    public static final String OPTIMIZELY_JWT = getEncryptedProperty("optimizely.jwt");
    public static final String OPTIMIZELY_PROJECT_ID = getProperty("optimizely.project.id");

    //Credentials WM Site
    public static final String DEFAULT_PASSWORD = getEncryptedProperty("default.password");
    public static final String BASIC_USER_EMAIL = getProperty("basic.user.email");
    public static final String BASIC_USER_LOGIN = getProperty("basic.user.login");
    public static final String BASIC_USER_PASSWORD = getEncryptedProperty("basic.user.password");
    public static final String BASIC_USER_ID = getProperty("basic.user.id");
    public static final String VERIFIED_USER_EMAIL = getProperty("verified.user.email");
    public static final String VERIFIED_USER_LOGIN = getProperty("verified.user.login");
    public static final String VERIFIED_USER_PASSWORD = getEncryptedProperty("verified.user.password");
    public static final String PHONE_USER_EMAIL = getProperty("phone.user.email");
    public static final String PHONE_USER_LOGIN = getProperty("phone.user.login");
    public static final String PHONE_USER_PASSWORD = getEncryptedProperty("phone.user.password");
    public static final String PHONE_USER_NAME = getProperty("phone.user.name");
    public static final String PHONE_USER_WITHOUT_MED_REC_EMAIL = getProperty("phone_no_med_rec.user.email");
    public static final String PHONE_USER_WITHOUT_MED_REC_LOGIN = getProperty("phone_no_med_rec.user.login");
    public static final String PHONE_USER_WITHOUT_MED_REC_PASSWORD = getEncryptedProperty("phone_no_med_rec.user.password");
    public static final String PHONE_USER_WITHOUT_MED_REC_NAME = getProperty("phone_no_med_rec.user.name");
    public static final String CHANGE_DATA_USER_LOGIN = getProperty("change_data.user.login");
    public static final String BRAND_OWNER_USER_LOGIN = getProperty("brand.owner.user.login");
    public static final String BRAND_OWNER_USER_PASSWORD = getEncryptedProperty("brand.owner.user.password");
    public static final String LISTING_OWNER_USER_ID = getProperty("listing.owner.user.id");
    public static final String LISTING_OWNER_USER_LOGIN = getProperty("listing.owner.user.login");
    public static final String LISTING_OWNER_USER_PASSWORD = getEncryptedProperty("listing.owner.user.password");
    public static final String TRASH_LISTING_OWNER_USER_LOGIN = getProperty("trash.listing.owner.user.login");
    public static final String LISTING_OWNER_OOS_USER_EMAIL = getProperty("listing.owner.oos.user.email");
    public static final String LISTING_OWNER_OOS_USER_LOGIN = getProperty("listing.owner.oos.user.login");
    public static final String LISTING_OWNER_OOS_USER_PASSWORD = getEncryptedProperty("listing.owner.oos.user.password");
    public static final String BASIC_USER_NO_FAVORITES_LOGIN = getProperty("basic.user.no_favorites.login");
    public static final String BASIC_USER_NO_FAVORITES_PASSWORD = getEncryptedProperty("basic.user.no_favorites.password");
    public static final String BASIC_USER_WITH_FAVORITES_LOGIN = getProperty("basic.user.with_favorites.login");
    public static final String BASIC_USER_WITH_FAVORITES_PASSWORD = getEncryptedProperty("basic.user.with_favorites.password");
    public static final String EMPTY_USER_LOGIN = getProperty("empty.user.login");
    public static final String SUPER_USER_LOGIN = getProperty("super.user.login");
    public static final String SUPER_USER_PASSWORD = getEncryptedProperty("super.user.password");
    public static final String SUPER_USER_ID = getProperty("super.user.id");
    public static final String TAXES_USER_LOGIN = getProperty("taxes.user.login");
    public static final String TAXES_USER_PASSWORD = getEncryptedProperty("taxes.user.password");
    public static final String SALES_MANAGER_USER_LOGIN = getProperty("sales.manager.user.login");
    public static final String SALES_MANAGER_USER_PASSWORD = getEncryptedProperty("sales.manager.user.password");
    public static final String SALES_MANAGER_USER_ID = getProperty("sales.manager.user.id");
    public static final String MODERATOR_USER_LOGIN = getProperty("moderator.user.login");
    public static final String MODERATOR_USER_PASSWORD = getEncryptedProperty("moderator.user.password");
    public static final String ONBOARDING_USER_LOGIN = getProperty("onboarding.user.login");
    public static final String ORGANIZATION_ADMIN_LOGIN = getProperty("organization.admin.login");
    public static final String ORGANIZATION_ADMIN_PASSWORD = getEncryptedProperty("organization.admin.password");
    public static final String ORGANIZATION_MANAGER_LOGIN = getProperty("organization.manager.login");
    public static final String ORGANIZATION_MANAGER_PASSWORD = getEncryptedProperty("organization.manager.password");
    public static final String ORGANIZATION_USER_FOR_EDIT_LOGIN = getProperty("organization.user.login");
    public static final String ORGANIZATION_USER_FOR_EDIT_PASSWORD = getEncryptedProperty("organization.user.password");
    public static final String ORGANIZATION_ASSOCIATE_LOGIN = getProperty("organization.associate.login");
    public static final String ORGANIZATION_ASSOCIATE_PASSWORD = getEncryptedProperty("organization.associate.password");
    public static final String ORGANIZATION_CAMPAIGN_MANAGER_LOGIN = getProperty("organization.campaign_manager.login");
    public static final String ORGANIZATION_CAMPAIGN_MANAGER_PASSWORD = getEncryptedProperty("organization.campaign_manager.password");
    public static final String USER_FOR_FAV_UNFAV_TESTS_LOGIN = getProperty("fav.unfav.tests.user.login");
    public static final String USER_FOR_FAV_UNFAV_TESTS_PASSWORD = getEncryptedProperty("fav.unfav.tests.user.password");
    public static final String USER_FOR_BAN_UNBAN_TESTS_LOGIN = getProperty("ban.unban.tests.user.login");
    public static final String USER_FOR_BAN_UNBAN_TESTS_PASSWORD = getEncryptedProperty("ban.unban.tests.user.password");
    public static final String USER_FOR_BAN_UNBAN_TESTS_ID = getProperty("ban.unban.tests.user.id");
    public static final String RECREATIONAL_USER_EMAIL = getProperty("recreational.user.email");
    public static final String RECREATIONAL_USER_LOGIN = getProperty("recreational.user.login");
    public static final String RECREATIONAL_USER_PASSWORD = getEncryptedProperty("recreational.user.password");
    public static final String IAM_SUPER_USER_LOGIN = getProperty("iam.admin.user.login");
    public static final String IAM_SUPER_USER_PASSWORD = getEncryptedProperty("iam.admin.user.password");
    public static final String POTIONEERS_SUPER_USER_LOGIN = getProperty("potioneers.super.user.login");
    public static final String POTIONEERS_SUPER_USER_PASSWORD = getEncryptedProperty("potioneers.admin.user.password");
    public static final String POTIONEERS_ADMIN_USER_LOGIN = getProperty("potioneers.admin.user.login");
    public static final String API_LOGISTICS_ADMIN_FOR_ROUTES_LOGIN = getProperty("api.logistics.admin.routes.login");
    public static final String API_LOGISTICS_ADMIN_FOR_ROUTES_PASSWORD = getEncryptedProperty("api.logistics.admin.routes.password");
    public static final String API_LOGISTICS_ADMIN_FOR_ROUTES_ID = getProperty("api.logistics.admin.routes.id");
    public static final String API_LOGISTICS_DRIVER_FOR_ROUTES_LOGIN = getProperty("api.logistics.driver.routes.login");
    public static final String API_LOGISTICS_DRIVER_FOR_ROUTES_PASSWORD = getEncryptedProperty("api.logistics.driver.routes.password");
    public static final String API_LOGISTICS_DRIVER_FOR_ROUTES_ID = getProperty("api.logistics.driver.routes.id");
    public static final String API_LOGISTICS_DRIVER_FOR_ONOFFDUTY_LOGIN = getProperty("api.logistics.driver.onoffduty.login");
    public static final String API_LOGISTICS_DRIVER_FOR_ONOFFDUTY_PASSWORD = getEncryptedProperty("api.logistics.driver.onoffduty.password");
    public static final String API_LOGISTICS_DRIVER_FOR_ONOFFDUTY_ID = getProperty("api.logistics.driver.onoffduty.id");
    public static final String ADMIN_WITH_LONG_USER_LOGIN = getProperty("longusername.user.login");
    public static final String ADMIN_WITH_LONG_USER_PASSWORD = getEncryptedProperty("longusername.user.password");
    public static final String ADMIN_WITH_LONG_USER_EMAIL = getProperty("longusername.user.email");
    //user which doesn't have any posts/reviews/followings and so on..
    public static final String FRESH_USER_ID = getProperty("fresh.user.id");
    public static final String FRESH_USER_LOGIN = getProperty("fresh.user.login");
    public static final String FRESH_USER_EMAIL = getProperty("fresh.user.email");
    public static final String FRESH_USER_PASSWORD = getEncryptedProperty("fresh.user.password");
    public static final String ALL_DATA_USER_LOGIN = getProperty("alldata.user.login");
    public static final String ALL_DATA_USER_EMAIL = getProperty("alldata.user.email");
    public static final String ALL_DATA_USER_PASSWORD = getEncryptedProperty("alldata.user.password");
    public static final String BANNED_USER_LOGIN = getProperty("banned.user.login");
    public static final String BANNED_USER_EMAIL = getProperty("banned.user.email");
    public static final String BANNED_USER_PASSWORD = getEncryptedProperty("banned.user.password");
    public static final String PHONE_ADMIN_USER_EMAIL = getProperty("phone_admin.user.email");
    public static final String PHONE_ADMIN_USER_LOGIN = getProperty("phone_admin.user.login");
    public static final String PHONE_ADMIN_USER_PASSWORD = getEncryptedProperty("phone_admin.user.password");
    public static final String DRIVER_APP_USER_EMAIL = getProperty("driverapp.user.email");
    public static final String DRIVER_APP_USER_LOGIN = getProperty("driverapp.user.login");
    public static final String DRIVER_APP_USER_PASSWORD = getEncryptedProperty("driverapp.user.password");
    public static final String DRIVER_APP_USER_PHONE = getProperty("driverapp.user.phone");
    public static final String DRIVER_APP_USER_NAME = getProperty("driverapp.user.name");
    public static final String DRIVER_APP_ADMIN_EMAIL = getProperty("driverapp.admin.email");
    public static final String DRIVER_APP_ADMIN_LOGIN = getProperty("driverapp.admin.login");
    public static final String DRIVER_APP_ADMIN_PASSWORD = getEncryptedProperty("driverapp.admin.password");
    public static final String DRIVER_APP_ADMIN_STORE_ID = getProperty("driverapp.admin.store.id");
    public static final String DRIVER_APP_ADMIN_STORE_CARD_ID = getProperty("driverapp.admin.store.card.id");
    public static final String DRIVER_APP_CHANGELOGIN_LOGIN_1 = getProperty("driverapp.changelogin.user.login1");
    public static final String DRIVER_APP_CHANGELOGIN_LOGIN_2 = getProperty("driverapp.changelogin.user.login2");
    public static final String DRIVER_APP_CHANGELOGIN_PASSWORD = getEncryptedProperty("driverapp.changelogin.user.password");
    public static final String DRIVER_APP_EMPTY_LOGIN = getProperty("driverapp.empty.user.login");
    public static final String DRIVER_APP_EMPTY_PASSWORD = getEncryptedProperty("driverapp.empty.user.password");
    public static final String DRIVER_APP_EMPTY_PHONE = getProperty("driverapp.empty.user.phone");
    public static final String DRIVER_APP_EMPTY_NAME = getProperty("driverapp.empty.user.name");
    public static final String DRIVER_APP_EMPTY_EMAIL = getProperty("driverapp.empty.user.email");
    public static final String DRIVER_APP_WITHOUT_NAME_LOGIN = getProperty("driverapp.without.name.user.login");
    public static final String DRIVER_APP_WITHOUT_NAME_PASSWORD = getEncryptedProperty("driverapp.without.name.user.password");
    public static final String DRIVER_APP_ITERABLE_COM_API_KEY = getEncryptedProperty("driverapp.iterable.com.api.key");
    public static final String JL_DELIVERY_TEAM_USERS_PASSWORD = getEncryptedProperty("jl.delivery.team.users.password");
    public static final String DRIVER_APP_USER_MANUAL_LOGIN = getProperty("driverapp.user.manual.login");
    public static final String DRIVER_APP_USER_MANUAL_PASSWORD = getEncryptedProperty("driverapp.user.manual.password");
    public static final String DRIVER_APP_ADMIN_MANUAL_LOGIN = getProperty("driverapp.admin.manual.login");
    public static final String DRIVER_APP_ADMIN_MANUAL_PASSWORD = getEncryptedProperty("driverapp.admin.manual.password");
    public static final String DRIVER_APP_ADMIN_MANUAL_STORE_ID = getProperty("driverapp.admin.manual.store.id");
    public static final String SUPER_USER_DELIVERY_EMAIL = getProperty("super.user.delivery.email");
    public static final String SUPER_USER_DELIVERY_LOGIN = getProperty("super.user.delivery.login");
    public static final String SUPER_USER_DELIVERY_PASSWORD = getEncryptedProperty("super.user.delivery.password");
    public static final String SUPER_USER_DELIVERY_ID = getProperty("super.user.delivery.id");
    public static final String USER_WITH_NUMERIC_NAME = getProperty("numeric.user.slug");
    public static final String API_ADMIN_MEMBER_ID = getProperty("api.admin.member.id");
    public static final String API_ADMIN_TEAM_MEMBER_ID = getProperty("api.admin.team.member.id");

    // WM_API Section
    // credentials
    public static final String API_ADMIN_LOGIN = getProperty("api.user.admin.login");
    public static final String API_BRAZE_USER_AUTH = getEncryptedProperty("api.user.braze_user.login");
    // urls
    public static final String API_BASE_API_WEB_V1_URL = getProperty("api.url.base_api_web_v1");
    public static final String API_BASE_API_V1_URL = getProperty("api.url.base_api_v1");
    public static final String API_API_G_OOS_URL = getProperty("api.url.api_g_oos");
    public static final String API_API_G_OOS_TAX_URL = API_API_G_OOS_URL + "taxes/";
    public static final String API_BASE_ADZERK_API_INSTANTCOUNTS_URL = getProperty("api.url.base_adzerk_api_instacounts");
    public static final String API_BASE_API_PHARMACOPEIA_V1_URL = getProperty("api.url.base_api_pharmacopeia_v1");
    public static final String API_API_G_WMNS_V1 = getProperty("api.url.api_g_wmns_v1");
    public static final String API_API_G_WM_V1 = getProperty("api.url.api_g_wm_v1");
    public static final String API_API_G_WM_V2 = getProperty("api.url.api_g_wm_v2");
    public static final String API_API_G_WM_WEB_V1 = getProperty("api.url.api_g_wm_web_v1");
    public static final String API_API_G_CORE_V2 = getProperty("api.url.api_g_core_v2");
    public static final String API_INTERNAL_API_G_CORE_V1 = getProperty("api.url.internal_api_g_core_v1");
    public static final String API_API_G_DISCOVERY_V1_URL = getProperty("api.url.api_g_discovery_v1");
    public static final String API_API_G_DISCOVERY_V2_URL = getProperty("api.url.api_g_discovery_v2");
    public static final String API_WM_API_URL = getProperty("api.url.wm_api");

    public static final String ANDROID_PHONE_NUMBER = getProperty("android.phone.number");
    public static final String ANDROID_PHONE_NUMBER_ALREADY_IN_USE = getProperty("android.phone.number.already.in.use");
    public static final String WM_CORE_CLIENT_ID = getProperty("wm.core.client.id");
    public static final String WM_CORE_CLIENT_SECRET = getEncryptedProperty("wm.core.client.secret");
    public static final String WM_RETAIL_CLIENT_ID = getProperty("wm.retail.client.id");
    public static final String WM_RETAIL_CLIENT_SECRET = getEncryptedProperty("wm.retail.client.secret");
    public static final String ORDERING_FOR_BLAZE_USER_LOGIN = getProperty("ordering.for.blaze.user.login");
    public static final String USER_AGENT = getProperty("user_agent");

    //WM Retail users
    public static final List<String> RETAIL_ORGANIZATIONS = getListProperty("retail.organizations");
    public static final String RETAIL_USERS_PASSWORD = getEncryptedProperty("retail.users.password");
    public static final String RETAIL_PASSWORD = getProperty("retail.password");
    public static final String RETAIL_ADMINISTRATOR_NAME = getProperty("retail.admin.name");
    public static final List<String> RETAIL_ADMINISTRATOR_EMAILS = getListPropertyWithPostfix("retail.administrators.login", getProperty("retail.users.email_postfix"));
    public static final List<String> RETAIL_ADMINISTRATOR_IDS = getListProperty("retail.administrators.id");
    public static final List<String> RETAIL_ADMINISTRATOR_PINS = getListProperty("retail.administrators.pin");
    public static final String RETAIL_STORE_MANAGER_NAME = getProperty("retail.storemanager.name");
    public static final List<String> RETAIL_STORE_MANAGER_EMAILS = getListPropertyWithPostfix("retail.store.managers.login", getProperty("retail.users.email_postfix"));
    public static final List<String> RETAIL_STORE_MANAGER_IDS = getListProperty("retail.store.managers.id");
    public static final List<String> RETAIL_STORE_MANAGER_PINS = getListProperty("retail.store.managers.pin");
    public static final String RETAIL_BUDTENDER_NAME = getProperty("retail.budtender.name");
    public static final List<String> RETAIL_BUDTENDER_EMAILS = getListPropertyWithPostfix("retail.budtenders.login", getProperty("retail.users.email_postfix"));
    public static final List<String> RETAIL_BUDTENDER_IDS = getListProperty("retail.budtenders.id");
    public static final List<String> RETAIL_BUDTENDER_PINS = getListProperty("retail.budtenders.pin");
    public static final String RETAIL_INVENTORY_CLERK_NAME = getProperty("retail.inventory.name");
    public static final List<String> RETAIL_INVENTORY_CLERK_EMAILS = getListPropertyWithPostfix("retail.inventory.clerks.login", getProperty("retail.users.email_postfix"));
    public static final List<String> RETAIL_INVENTORY_CLERK_IDS = getListProperty("retail.inventory.clerks.id");
    public static final List<String> RETAIL_INVENTORY_CLERK_PINS = getListProperty("retail.inventory.clerks.pin");
    public static final String RETAIL_FRONT_DESK_CLERK_NAME = getProperty("retail.frontdesk.name");
    public static final List<String> RETAIL_FRONT_DESK_CLERK_EMAILS = getListPropertyWithPostfix("retail.frontdesk.clerks.login", getProperty("retail.users.email_postfix"));
    public static final List<String> RETAIL_FRONT_DESK_CLERK_IDS = getListProperty("retail.frontdesk.clerks.id");
    public static final List<String> RETAIL_FRONT_DESK_CLERK_PINS = getListProperty("retail.frontdesk.clerks.pin");
    public static final String RETAIL_AUDITOR_NAME = getProperty("retail.auditor.name");
    public static final List<String> RETAIL_AUDITOR_EMAILS = getListPropertyWithPostfix("retail.auditors.login", getProperty("retail.users.email_postfix"));
    public static final List<String> RETAIL_AUDITOR_IDS = getListProperty("retail.auditors.id");
    public static final List<String> RETAIL_AUDITOR_PINS = getListProperty("retail.auditors.pin");
    public static final String RETAIL_FORGOT_PASSWORD_NAME = getProperty("retail.forgot.password.name");
    public static final List<String> RETAIL_FORGOT_PASSWORD_EMAILS = getListPropertyWithPostfix("retail.forgot.administrators.login", getProperty("retail.users.email_postfix"));
    public static final List<String> RETAIL_FORGOT_PASSWORD_IDS = getListProperty("retail.forgot.administrators.id");
    public static final List<String> RETAIL_FORGOT_PASSWORD_PINS = getListProperty("retail.forgot.administrators.pin");
    public static final String RETAIL_CHANGE_PASSWORD_NAME = getProperty("retail.passchange.name");
    public static final List<String> RETAIL_CHANGE_PASSWORD_EMAILS = getListPropertyWithPostfix("retail.password.administrators.login", getProperty("retail.users.email_postfix"));
    public static final List<String> RETAIL_CHANGE_PASSWORD_IDS = getListProperty("retail.password.administrators.id");
    public static final List<String> RETAIL_CHANGE_PASSWORD_PINS = getListProperty("retail.password.administrators.pin");
    public static final String RETAIL_USER = getProperty("retail.user");
    public static final String RETAIL_ADMIN_1_FOR_PERFORMANCE_ORG = getProperty("retail.admin1.for.performance.org");
    public static final String RETAIL_ADMIN_2_FOR_PERFORMANCE_ORG = getProperty("retail.admin2.for.performance.org");
    public static final String RETAIL_ADMIN_FOR_ORDERING_HYBRID_ORG = getProperty("retail.admin.for.ordering.hybrid.org");

    // Content
    public static final String NEWS_PAGE = BASE_PAGE + "news/";
    public static final String LEARN_PAGE = BASE_PAGE + "learn";
    public static final String WEEDFACTS_PAGE = BASE_PAGE + "weedfacts/";
    public static final String THE_EXIT_DRUG_PAGE = "https://theexitdrug.com/";
    public static final String POLICY_PAGE = "http://wmpolicystage.wpengine.com/";
    public static final String HIGH_HONOR_ROLL_PAGE = BASE_PAGE + "high-honor-roll/";
    public static final String OPENCA_PAGE = BASE_PAGE + "openca/";
    public static final String THE_MUSEUM_OF_WEED = "https://www.themuseumofweed.com/";
    public static final String WM_EXCHANGE_MICROSITE = "https://wmbizzver2.wmmota.com";
    // following properties should be checked for relevance
    public static final String CONTENT_DEFAULT_PASSWORD = getEncryptedProperty("news.password");
    public static final String MUSEUM_OF_WEED_PASSWORD = getEncryptedProperty("museum.of.weed.password");
    public static final String WORDPRESS_NEWS_USERNAME = getProperty("wordpress.user");
    public static final String WORDPRESS_NEWS_PASSWORD = getEncryptedProperty("wordpress.password");

    //Credentials Delivery
    public static final String DELIVERY_WM_API_KEY = getEncryptedProperty("delivery.wm.api.key");
    public static final String DELIVERY_GET_JWT_LOGIN = getProperty("delivery.get.jwt.login");
    public static final String DELIVERY_GET_JWT_PASSWORD = getEncryptedProperty("delivery.get.jwt.password");
    public static final String DELIVERY_API_GET_JWT_LOGIN = getProperty("delivery.api.get.jwt.login");
    public static final String DELIVERY_USER_LOGIN = getProperty("delivery.user.login");
    public static final String DELIVERY_USER_EMAIL = getProperty("delivery.user.email");
    public static final String DELIVERY_USER_PASSWORD = getEncryptedProperty("delivery.user.password");

    //Credentials DataAnalytics
    public static final String DATA_ANALYTICS_USER = getProperty("data_analytics.user.login");
    public static final String DATA_ANALYTICS_USER_ID = getProperty("data_analytics.user.id");
    public static final String DATA_ANALYTICS_USER_PASSWORD = getEncryptedProperty("data_analytics.user.password");
    public static final String API_AMPLITUDE_RESPONSITIVE_USER_ID = getProperty("data_analytics.amplitude.responsitive.user.id");
    public static final String API_AMPLITUDE_APP_USER_ID = getProperty("data_analytics.amplitude.app.user.id");
    public static final String DATA_ANALYTICS_USER_2 = getProperty("data_analytics.user_2.login");
    public static final String DATA_ANALYTICS_USER_2_ID = getProperty("data_analytics.user_2.id");
    public static final String DATA_ANALYTICS_USER_2_PASSWORD = getEncryptedProperty("data_analytics.user_2.password");
    public static final String API_AMPLITUDE_RESPONSITIVE_USER_2_ID = getProperty("data_analytics.amplitude.responsitive.user_2.id");
    public static final String API_AMPLITUDE_APP_USER_2_ID = getProperty("data_analytics.amplitude.app.user_2.id");

    //Credentials Instagram
    public static final String INSTAGRAM_BASIC_USER_LOGIN = getProperty("instagram.basic.user.login");
    public static final String INSTAGRAM_BASIC_USER_PASSWORD = getEncryptedProperty("instagram.basic.user.password");
    public static final String INSTAGRAM_BASIC_USER_EMAIL = getProperty("instagram.basic.user.email");

    //Credentials Mailtrap
    public static final String MAILTRAP_JWT_TOKEN = getEncryptedProperty("mailtrap.jwt.token");
    public static final String MAILTRAP_TOKEN = getEncryptedProperty("mailtrap.token");

    //Credentials Tookan
    public static final String TOOKAN_ADMIN_USERNAME = getProperty("tookan.admin.username");
    public static final String TOOKAN_ADMIN_PASSWORD = getEncryptedProperty("tookan.admin.password");

    //Credentials Blaze Dashboard 'https://retail.blaze.me/'
    public static final String BLAZE_USER_LOGIN = getProperty("blaze.user.login");
    public static final String BLAZE_USER_PASSWORD = getEncryptedProperty("blaze.user.password");
    public static final String BLAZE_API_URL_PREFIX = getProperty("blaze.api.url.prefix");

    //Credentials Flowhub 'https://cashier.flowhub.co/'
    public static final String FLOWHUB_USER_LOGIN = getProperty("flowhub.user.login");
    public static final String FLOWHUB_USER_PASSWORD = EnvConstants.DEFAULT_PASSWORD;

    //Credentials integrators-service
    public static final String API_OOS_INTEGRATORS_SERVICE_INTEGRATIONS_URL_PREFIX = getProperty("api.oos.integrators_service.integrations.url.prefix");
    public static final String API_OOS_INTEGRATORS_SERVICE_CALLBACK_URL = getProperty("api.oos.integrators_service.callback_url");
    public static final String API_OOS_INTEGRATORS_SERVICE_INTEGRATION_ID_RETAIL = getProperty("api.oos.integrators_service.integration_id.retail");
    public static final String API_OOS_INTEGRATORS_SERVICE_USER_TOKEN = getProperty("api.oos.integrators_service.user_token");
    public static final String API_OOS_INTEGRATORS_URL_PREFIX = getProperty("api.oos.integrators.url.prefix");
    public static final String API_OOS_INTEGRATION_ID_FLOWHUB = getProperty("api.oos.integration_id.flowhub");

    //API
    public static final String API_BASE_URL_PREFIX = getProperty("api.url.prefix");
    public static final String API_CORE_URL_PREFIX = getProperty("api.core.url.prefix");
    public static final String API_CORE_V1_URL_PREFIX = getProperty("api.core.v1.url.prefix");
    public static final String API_CORE_V2_URL_PREFIX = getProperty("api.core.v2.url.prefix");
    public static final String API_EXCHANGE_URL_PREFIX = getProperty("api.exchange.url.prefix");
    public static final String API_RETAIL_URL_PREFIX = getProperty("api.retail.url.prefix");
    public static final String API_BASE_CORE_URL_PREFIX = getProperty("api.base.core.url.prefix");
    public static final String API_WMNS_CORE_URL_PREFIX = getProperty("api.wmns.core.url.prefix");
    public static final String API_ADZERK_INSTANTCOUNTS_URL_PREFIX = getProperty("api.adzerk.instantcounts.url.prefix");
    public static final String API_V2_BASE_URL_PREFIX = getProperty("api.wmapi_v2_base.url.prefix");
    public static final String API_V2_URL_PREFIX = getProperty("api.wmapi_v2.url.prefix");
    public static final String API_WM_DISCOVERY_V1_URL_PREFIX = getProperty("api.url.discovery.v1.prefix");
    public static final String API_DISCOVERY_V1_URL_PREFIX = getProperty("api.discovery.v1.prefix");
    public static final String API_DISCOVERY_V2_URL_PREFIX = getProperty("api.discovery.v2.prefix");
    public static final String API_WM_API_REMOVED_URL_PREFIX = getProperty("wm.api.v2.prefix");
    public static final String API_OAUTH_URL = getProperty("api.oauth.url");
    public static final String API_OOS_INTERNAL_URL_PREFIX = getProperty("api.oos.internal.url.prefix");
    public static final String API_OOS_GQL_URL_PREFIX = API_OOS_INTERNAL_URL_PREFIX + "/gql";
    public static final String API_OOS_URL_PREFIX = getProperty("api.oos.url.prefix");
    public static final String API_OOS_WM_PLATFORM_USERS_ENC_DOCUMENTS_PREFIX = getProperty("api.oos.enc.documents.prefix");
    public static final String API_URL_PARACHUTE_DISCOUNT_CODES_DOC = getProperty("api.url.parachute.discount.codes.doc");
    public static final String API_WEB_V1_URL_PREFIX = getProperty("api.web_v1.url.prefix");
    public static final String API_BASE_WEB_V1_URL_PREFIX = getProperty("api.base.web_v1.url.prefix");
    public static final String API_V1_URL_PREFIX = getProperty("api.v1.url.prefix");
    public static final String API_WEEDMAPS_DOCUMENTS = getProperty("api.weedmaps.documents");
    public static final String API_WEEDMAPS_HEALTHZ = getProperty("api.weedmaps.healthz");
    public static final String API_MOBILE_V1_2_URL_PREFIX = getProperty("api.mobile_v1_2.url.prefix");
    public static final String API_BASE_MOBILE_V1_2_URL_PREFIX = getProperty("api.base.mobile_v1_2.url.prefix");
    public static final String API_MOBILE_V1_1_URL_PREFIX = getProperty("api.mobile_v1_1.url.prefix");
    public static final String API_BASE_MOBILE_V1_1_URL_PREFIX = getProperty("api.base.mobile_v1_1.url.prefix");
    public static final String API_PHARMACOPEIA_V1_URL_PREFIX = getProperty("api.pharmacopeia_v1.url.prefix");
    public static final String API_ELASTICSEARCH_URL_PREFIX = getProperty("api.elasticsearch.url.prefix");
    public static final String API_ES_URL_PREFIX = getProperty("api.es.url.prefix");
    public static final String API_MOBILE_WEB_V1_URL_PREFIX = getProperty("api.mobile_web_v1.url.prefix");
    public static final String API_PLATFORM_GQL_URL_PREFIX = getProperty("api.platform.gql.url.prefix");
    public static final String API_AMPLITUDE_URL_PREFIX = getProperty("api.amplitude.url.prefix");
    public static final String API_MAILTRAP_URL_PREFIX = getProperty("api.mailtrap.url.prefix");
    public static final String WEB_MAILTRAP_URL_PREFIX = getProperty("web.mailtrap.url.prefix");
    public static final String API_MAILTRAP_MAILBOX_ID = getProperty("api.mailtrap.mailbox.id");
    public static final String API_LOGISTIC_SERVICE_INTERNAL_PREFIX = getProperty("api.logistic.service.internal.prefix");
    public static final String API_INTERNAL_API_G_LOGISTIC_SERVICE_PREFIX = getProperty("api.internal.api.g.logistic.service.prefix");
    public static final String API_WSS_LOGISTIC_SERVICE_INTERNAL_PREFIX = getProperty("api.wss.logistic.service.internal.prefix");
    public static final String API_WS_LOGISTIC_SERVICE_INTERNAL_PREFIX = getProperty("api.ws.logistic.service.internal.prefix");
    public static final String API_LOGISTIC_SERVICE_PREFIX = getProperty("api.logistic.service.prefix");
    public static final String API_TAX_SERVICE_PREFIX = API_OOS_URL_PREFIX + "/taxes/";
    public static final String API_CONTENT_PREFIX = getProperty("api.content.prefix");
    public static final String API_TWITTER = "https://api.twitter.com/1.1/search/tweets.json";
    public static final String API_ITERABLE = getProperty("api.iterable.prefix");
    public static final String API_WSS_RETAIL_SERVICE_INTERNAL_PREFIX = getProperty("api.wss.retail.service.internal.prefix");
    public static final String RETAIL_API_SERVICE_INTERNAL_PREFIX = getProperty("retail.api.service.internal.prefix");
    public static final String RETAIL_API_LOGIN_POSTFIX = getProperty("retail.api.login.postfix");
    public static final String API_G_ENV_INTERNAL_WEEDMAPS_COM = getProperty("api_g.internal_weedmaps.com");
    public static final String API_V2_INTERNAL_WM = getProperty("api.v2.internal_weedmaps.com");

    // API data items
    public static final String LAB_API_KEY = getEncryptedProperty("api.lab.api_key");
    public static final String OVERRIDE_WM_MENU_PRIORITIZATION_API_KEY = getEncryptedProperty("api.dispensary.ovveride_menu_prioritization.api_key");
    public static final String API_UNPUBLISHED_LISTINGS_WITH_BRANDS_WMID = getProperty("api.dispensary.unpublished.withbrands.wmid");
    public static final String API_UNPUBLISHED_LISTINGS_WITH_BRANDS_ID = getProperty("api.dispensary.unpublished.withbrands.id");
    public static final String API_UNPUBLISHED_LISTINGS_WITH_BRANDS_NAME = getProperty("api.dispensary.unpublished.withbrands.name");
    public static final String API_UNPUBLISHED_LISTINGS_WITH_BRANDS_SLUG = getProperty("api.dispensary.unpublished.withbrands.slug");
    public static final String REGION_SLUG_WITH_NUMERIC = getProperty("api.region.with.numeric");
    public static final String BEAM_CLIENT_ID = getProperty("beam.client.id");
    public static final String BEAM_CLIENT_SECRET = getProperty("beam.client.secret");
    public static final String BEAM_CLIENT_ROLE_ID = getProperty("beam.client.role_id");
    public static final String BEAM_ADMIN_ROLE_ID = getProperty("beam.admin.role_id");
    public static final String BEAM_QA_TEST_ROLE_ID = getProperty("beam.qa_test_user.role_id");
    public static final String BEAM_FEATURE_FLAG_GET_GROUP_ID = getProperty("beam.feature_flag.get.group_id");
    public static final String BEAM_FEATURE_FLAG_PATCH_FLAG_ID = getProperty("beam.feature_flag.patch.flag_id");
    public static final String BEAM_FEATURE_FLAG_PATCH_GROUP_ID = getProperty("beam.feature_flag.patch.group_id");
    public static final String POTIONEERS_CLIENT_ID = getProperty("potioneers.client_id");
    public static final String POTIONEERS_CLIENT_SECRET = getProperty("potioneers.client.secret");
    public static final String HARVEST_ON_GEARY_API_KEY = getEncryptedProperty("harvestongeary.api.key");
    public static final String EFFECT_TAG_SLEEPY_ID = getProperty("effect.tag.sleepy.id");
    public static final String EFFECT_TAG_RELAXED_ID = getProperty("effect.tag.relaxed.id");
    public static final String EFFECT_TAG_HAPPY_ID = getProperty("effect.tag.happy.id");
    public static final String FLAVOR_TAG_FLOWERY_ID = getProperty("flavor.tag.flowery.id");
    public static final String FLAVOR_TAG_CITRUS_ID = getProperty("flavor.tag.citrus.id");

    //API OOS
    public static final String API_OOS_DELIVERY_NAME = getProperty("api.oos.delivery.name");
    public static final String API_OOS_DELIVERY_SLUG = getProperty("api.oos.delivery.slug");
    public static final String API_OOS_DELIVERY_WMID = getProperty("api.oos.delivery.wmid");
    public static final String API_OOS_DELIVERY_ID = getProperty("api.oos.delivery.id");
    public static final String API_OOS_DELIVERY_ACCOUNT_ID = getProperty("api.oos.delivery.account.id");
    public static final String API_OOS_DELIVERY_MENU_ITEM1_ID = getProperty("api.oos.delivery.menu.item1.id");
    public static final String API_OOS_DELIVERY_MENU_ITEM1_NAME = getProperty("api.oos.delivery.menu.item1.name");
    public static final String API_OOS_DELIVERY_MENU_ITEM2_ID = getProperty("api.oos.delivery.menu.item2.id");
    public static final String API_OOS_DELIVERY_MENU_ITEM3_ID = getProperty("api.oos.delivery.menu.item3.id");
    public static final String API_OOS_DELIVERY_MENU_ITEM_WITH_CUSTOM_WEIGHT_ID = getProperty("api.oos.delivery.menu.item_with_custom_weight.id");
    public static final String API_OOS_DELIVERY_ADDRESS = getProperty("api.oos.delivery.address");
    public static final String API_OOS_DELIVERY_LATITUDE = getProperty("api.oos.delivery.latitude");
    public static final String API_OOS_DELIVERY_LONGITUDE = getProperty("api.oos.delivery.longitude");
    public static final String API_OOS_DELIVERY_DISABLE_FOR_OO_WMID = getProperty("api.oos.delivery.disable.for.oo.wmid");
    public static final String API_OOS_DELIVERY_WITHOUT_TAX_SLUG = getProperty("api.oos.delivery.without.tax.slug");
    public static final String API_OOS_DISPENSARY_NAME = getProperty("api.oos.dispensary.name");
    public static final String API_OOS_DISPENSARY_SLUG = getProperty("api.oos.dispensary.slug");
    public static final String API_OOS_DISPENSARY_ID = getProperty("api.oos.dispensary.id");
    public static final String API_OOS_DISPENSARY_WMID = getProperty("api.oos.dispensary.wmid");
    public static final String API_OOS_DISPENSARY_MENU_ITEM1_ID = getProperty("api.oos.dispensary.menu.item1.id");
    public static final String API_OOS_DISPENSARY_MENU_ITEM2_ID = getProperty("api.oos.dispensary.menu.item2.id");
    public static final String API_OOS_DISPENSARY_MENU_ITEM3_ID = getProperty("api.oos.dispensary.menu.item3.id");
    public static final String API_OOS_DISPENSARY_CANADA_NAME = getProperty("api.oos.dispensary.canada.name");
    public static final String API_OOS_DISPENSARY_CANADA_SLUG = getProperty("api.oos.dispensary.canada.slug");
    public static final String API_OOS_DISPENSARY_CANADA_ORG_ID = getProperty("api.oos.dispensary.canada.org.id");
    public static final String API_OOS_DISPENSARY_CANADA_ID = getProperty("api.oos.dispensary.canada.id");
    public static final String API_OOS_DISPENSARY_CANADA_WMID = getProperty("api.oos.dispensary.canada.wmid");
    public static final String API_OOS_DISPENSARY_CANADA_MENU_ITEM1_ID = getProperty("api.oos.dispensary.canada.menu.item1.id");
    public static final String API_OOS_DISPENSARY_HYBRID_NAME = getProperty("api.oos.dispensary.hybrid.name");
    public static final String API_OOS_DISPENSARY_HYBRID_SLUG = getProperty("api.oos.dispensary.hybrid.slug");
    public static final String API_OOS_DISPENSARY_HYBRID_MEDICAL_MENU_ITEM_ID = getProperty("api.oos.dispensary.hybrid.medical.menu.item.id");
    public static final String API_OOS_DISPENSARY_HYBRID_RECREATIONAL_MENU_ITEM_ID = getProperty("api.oos.dispensary.hybrid.recreational.menu.item.id");

    //API Swagger validation
    public static final String SWAGGER_WM_DISCOVERY_API_V1 = getProperty("swagger.wm.discovery.api.v1");

    // API Logistics data items
    public static final String API_LOGISTICS_DELIVERY_JL_WMID = getProperty("api.logistics.delivery_jl.wmid");
    public static final String API_LOGISTICS_DELIVERY_JL_ID = getProperty("api.logistics.delivery_jl.id");
    public static final String API_LOGISTICS_DELIVERY_JL_API_KEY = getProperty("api.logistics.delivery_jl.api_key");
    public static final String API_LOGISTICS_DELIVERY_JL_ADMIN_LOGIN = getProperty("api.logistics.delivery_jl.admin.login");
    public static final String API_LOGISTICS_DELIVERY_JL_DRIVER1_LOGIN = getProperty("api.logistics.delivery_jl.driver1.login");
    public static final String API_LOGISTICS_DELIVERY_JL_ADMIN_ID = getProperty("api.logistics.delivery_jl.admin.id");
    public static final String API_LOGISTICS_DELIVERY_JL_DRIVER1_ID = getProperty("api.logistics.delivery_jl.driver1.id");
    public static final String API_LOGISTICS_DELIVERY_JL_ADMIN_CARD_ID = getProperty("api.logistics.delivery_jl.admin.card_id");
    public static final String API_LOGISTICS_DELIVERY_JL_DRIVER1_CARD_ID = getProperty("api.logistics.delivery_jl.driver1.card_id");
    public static final String API_LOGISTICS_DELIVERY_JL_PASSWORD = getEncryptedProperty("api.logistics.delivery_jl.password");

    //Amplitude API
    public static final String API_AMPLITUDE_RESPONSITIVE_API_KEY = getEncryptedProperty("amplitude.responsitive.api_key");
    public static final String API_AMPLITUDE_RESPONSITIVE_SECRET_KEY = getEncryptedProperty("amplitude.responsitive.secret_key");
    public static final String API_AMPLITUDE_BASIC_AUTH_TOKEN = getEncryptedProperty("amplitude.responsitive.basic_authorization_token");
    public static final String API_AMPLITUDE_APP_API_KEY = getEncryptedProperty("amplitude.app.api_key");
    public static final String API_AMPLITUDE_APP_SECRET_KEY = getEncryptedProperty("amplitude.app.secret_key");
    public static final String API_AMPLITUDE_USER_ID = getProperty("data_analytics.amplitude.user.id");
    public static final String API_AMPLITUDE_APP_AUTH_TOKEN = getEncryptedProperty("amplitude.app.basic_authorization_token");

    //Logistic
    public static final String LOGISTIC_OPS_MANAGER_LOGIN = getProperty("logistic.ops.manager.user.login");
    public static final String LOGISTIC_OPS_MANAGER_PASSWORD = DEFAULT_PASSWORD;
    public static final String LOGISTIC_MULTI_ADMIN_LOGIN = getProperty("logistic.multi.admin.user.login");
    public static final String LOGISTIC_MULTI_ADMIN_PASSWORD = DEFAULT_PASSWORD;
    public static final String LOGISTIC_PICKUP_USER_LOGIN = getProperty("logistic.pickup.user.login");
    public static final String LOGISTIC_PICKUP_USER_PASSWORD = DEFAULT_PASSWORD;
    public static final String LOGISTIC_PICKUP_USER_ID = getProperty("logistic.pickup.user.id");
    public static final String LOGISTIC_PICKUP_USER_DOCUMENT_ID = getProperty("logistic.pickup.user.document_id");
    public static final String LOGISTIC_PICKUP_USER_DOCUMENT_REC = getProperty("logistic.pickup.user.document_rec");
    public static final String LOGISTIC_PICKUP_DISPENSARY = getProperty("logistic.pickup.dispensary");
    public static final String LOGISTIC_PICKUP_DISPENSARY_WMID = getProperty("logistic.pickup.dispensary.wmid");
    public static final String LOGISTIC_ORDER_USER_LOGIN = getProperty("logistic.order.user.login");
    public static final String LOGISTIC_ORDER_USER_PASSWORD = DEFAULT_PASSWORD;
    public static final String LOGISTIC_ORDER_DELIVERY = getProperty("logistic.order.delivery");
    public static final String LOGISTIC_ORDER_DELIVERY_WMID = getProperty("logistic.order.delivery.wmid");
    public static final String LOGISTIC_ORDER_DRIVER_LOGIN = getProperty("logistic.order.driver.login");
    public static final String LOGISTIC_ORDER_DRIVER_PASSWORD = DEFAULT_PASSWORD;
    public static final String LOGISTIC_USER_FOR_DIFFERENT_ROLES_LOGIN = getProperty("logistic.user.for.different.roles.login");
    public static final String LOGISTIC_USER_FOR_DIFFERENT_ROLES_PASSWORD = DEFAULT_PASSWORD;
    public static final String LOGISTIC_USER_FOR_DIFFERENT_ROLES_ID = getProperty("logistic.user.for.different.roles.id");

    // Logistics recipient data
    public static final String LOGISTIC_RECIPIENT_LOGIN = getProperty("logistic.recipient.login");
    public static final String LOGISTIC_RECIPIENT_PASSWORD = DEFAULT_PASSWORD;
    public static final String LOGISTIC_RECIPIENT_ID = getProperty("logistic.recipient.id");
    public static final String LOGISTIC_RECIPIENT_MEDREC_ID = getProperty("logistic.recipient.medrec_id");
    public static final String LOGISTIC_RECIPIENT_DRIVER_LICENSE_ID = getProperty("logistic.recipient.driver_license_id");

    // No logistics delivery data
    public static final String NO_LOGISTICS_DELIVERY_WMID = getProperty("no.logistics.delivery.wmid");

    // Read only delivery team
    public static final String RO_DELIVERY_WMID = getProperty("logistic.ro_delivery.wmid");
    public static final String RO_DELIVERY_NAME = getProperty("logistic.ro_delivery.name");
    public static final String RO_DELIVERY_TEAM_ID = getProperty("logistic.ro_delivery.team_id");
    public static final String RO_DELIVERY_TEAM_ADMIN_LOGIN = getProperty("logistic.ro_delivery_team_admin.login");
    public static final String RO_DELIVERY_TEAM_ADMIN_ID = getProperty("logistic.ro_delivery_team_admin.id");
    public static final String RO_DELIVERY_TEAM_ADMIN_CARD_ID = getProperty("logistic.ro_delivery_team_admin.card_id");
    public static final String RO_DELIVERY_TEAM_DRIVER_1_LOGIN = getProperty("logistic.ro_delivery_team_driver1.login");
    public static final String RO_DELIVERY_TEAM_DRIVER_1_ID = getProperty("logistic.ro_delivery_team_driver1.id");
    public static final String RO_DELIVERY_TEAM_DRIVER_1_CARD_ID = getProperty("logistic.ro_delivery_team_driver1.card_id");

    // Single delivery team data
    public static final String SINGLE_DELIVERY_WMID = getProperty("logistic.single_delivery.wmid");
    public static final String SINGLE_DELIVERY_NAME = getProperty("logistic.single_delivery.name");
    public static final String SINGLE_DELIVERY_TEAM_ID = getProperty("logistic.single_delivery.team_id");
    public static final String SINGLE_DELIVERY_TEAM_ADMIN_LOGIN = getProperty("logistic.single_delivery_team_admin.login");
    public static final String SINGLE_DELIVERY_TEAM_ADMIN_ID = getProperty("logistic.single_delivery_team_admin.id");
    public static final String SINGLE_DELIVERY_TEAM_ADMIN_CARD_ID = getProperty("logistic.single_delivery_team_admin.card_id");
    public static final String SINGLE_DELIVERY_TEAM_DRIVER_1_LOGIN = getProperty("logistic.single_delivery_team_driver1.login");
    public static final String SINGLE_DELIVERY_TEAM_DRIVER_1_ID = getProperty("logistic.single_delivery_team_driver1.id");
    public static final String SINGLE_DELIVERY_TEAM_DRIVER_1_CARD_ID = getProperty("logistic.single_delivery_team_driver1.card_id");
    public static final String SINGLE_DELIVERY_TEAM_DRIVER_2_LOGIN = getProperty("logistic.single_delivery_team_driver2.login");
    public static final String SINGLE_DELIVERY_TEAM_DRIVER_2_ID = getProperty("logistic.single_delivery_team_driver2.id");
    public static final String SINGLE_DELIVERY_TEAM_DRIVER_2_CARD_ID = getProperty("logistic.single_delivery_team_driver2.card_id");
    public static final String SINGLE_DELIVERY_TEAM_DRIVER_3_LOGIN = getProperty("logistic.single_delivery_team_driver3.login");
    public static final String SINGLE_DELIVERY_TEAM_DRIVER_3_ID = getProperty("logistic.single_delivery_team_driver3.id");
    public static final String SINGLE_DELIVERY_TEAM_DRIVER_3_CARD_ID = getProperty("logistic.single_delivery_team_driver3.card_id");
    public static final String SINGLE_DELIVERY_TEAM_DRIVER_4_LOGIN = getProperty("logistic.single_delivery_team_driver4.login");
    public static final String SINGLE_DELIVERY_TEAM_DRIVER_4_ID = getProperty("logistic.single_delivery_team_driver4.id");
    public static final String SINGLE_DELIVERY_TEAM_DRIVER_4_CARD_ID = getProperty("logistic.single_delivery_team_driver4.card_id");
    public static final String SINGLE_DELIVERY_TEAM_DRIVER_5_LOGIN = getProperty("logistic.single_delivery_team_driver5.login");
    public static final String SINGLE_DELIVERY_TEAM_DRIVER_5_ID = getProperty("logistic.single_delivery_team_driver5.id");
    public static final String SINGLE_DELIVERY_TEAM_DRIVER_5_CARD_ID = getProperty("logistic.single_delivery_team_driver5.card_id");
    public static final String SINGLE_DELIVERY_TEAM_PASSWORD = getEncryptedProperty("logistic.single_delivery_team.password");

    // Logistics Multi Delivery team data
    public static final String MULTI_DELIVERY_TEAM_ADMIN_LOGIN = getProperty("logistic.multi_delivery_team_admin.login");
    public static final String MULTI_DELIVERY_TEAM_ADMIN_ID = getProperty("logistic.multi_delivery_team_admin.id");
    public static final String MULTI_DELIVERY_TEAM_ADMIN_CARD_ID = getProperty("logistic.multi_delivery_team_admin.card_id");
    public static final String MULTI_DELIVERY1_WMID = getProperty("logistic.multi_delivery1.wmid");
    public static final String MULTI_DELIVERY1_NAME = getProperty("logistic.multi_delivery1.name");
    public static final String MULTI_DELIVERY1_TEAM_ID = getProperty("logistic.multi_delivery1.team_id");
    public static final String MULTI_DELIVERY2_WMID = getProperty("logistic.multi_delivery2.wmid");
    public static final String MULTI_DELIVERY2_NAME = getProperty("logistic.multi_delivery2.name");
    public static final String MULTI_DELIVERY2_TEAM_ID = getProperty("logistic.multi_delivery2.team_id");
    public static final String MULTI_DELIVERY3_WMID = getProperty("logistic.multi_delivery3.wmid");
    public static final String MULTI_DELIVERY3_NAME = getProperty("logistic.multi_delivery3.name");
    public static final String MULTI_DELIVERY3_TEAM_ID = getProperty("logistic.multi_delivery3.team_id");
    public static final String MULTI_DELIVERY4_WMID = getProperty("logistic.multi_delivery4.wmid");
    public static final String MULTI_DELIVERY4_NAME = getProperty("logistic.multi_delivery4.name");
    public static final String MULTI_DELIVERY4_TEAM_ID = getProperty("logistic.multi_delivery4.team_id");
    public static final String MULTI_DELIVERY5_WMID = getProperty("logistic.multi_delivery5.wmid");
    public static final String MULTI_DELIVERY5_NAME = getProperty("logistic.multi_delivery5.name");
    public static final String MULTI_DELIVERY5_TEAM_ID = getProperty("logistic.multi_delivery5.team_id");
    public static final String MULTI_DELIVERY_TEAM_DRIVER_1_LOGIN = getProperty("logistic.multi_delivery_team_driver1.login");
    public static final String MULTI_DELIVERY_TEAM_DRIVER_1_ID = getProperty("logistic.multi_delivery_team_driver1.id");
    public static final String MULTI_DELIVERY_TEAM_DRIVER_1_CARD_ID = getProperty("logistic.multi_delivery_team_driver1.card_id");
    public static final String MULTI_DELIVERY_TEAM_DRIVER_2_LOGIN = getProperty("logistic.multi_delivery_team_driver2.login");
    public static final String MULTI_DELIVERY_TEAM_DRIVER_2_ID = getProperty("logistic.multi_delivery_team_driver2.id");
    public static final String MULTI_DELIVERY_TEAM_DRIVER_2_CARD_ID = getProperty("logistic.multi_delivery_team_driver2.card_id");
    public static final String MULTI_DELIVERY_TEAM_DRIVER_3_LOGIN = getProperty("logistic.multi_delivery_team_driver3.login");
    public static final String MULTI_DELIVERY_TEAM_DRIVER_3_ID = getProperty("logistic.multi_delivery_team_driver3.id");
    public static final String MULTI_DELIVERY_TEAM_DRIVER_3_CARD_ID = getProperty("logistic.multi_delivery_team_driver3.card_id");
    public static final String MULTI_DELIVERY_TEAM_DRIVER_4_LOGIN = getProperty("logistic.multi_delivery_team_driver4.login");
    public static final String MULTI_DELIVERY_TEAM_DRIVER_4_ID = getProperty("logistic.multi_delivery_team_driver4.id");
    public static final String MULTI_DELIVERY_TEAM_DRIVER_4_CARD_ID = getProperty("logistic.multi_delivery_team_driver4.card_id");
    public static final String MULTI_DELIVERY_TEAM_DRIVER_5_LOGIN = getProperty("logistic.multi_delivery_team_driver5.login");
    public static final String MULTI_DELIVERY_TEAM_DRIVER_5_ID = getProperty("logistic.multi_delivery_team_driver5.id");
    public static final String MULTI_DELIVERY_TEAM_DRIVER_5_CARD_ID = getProperty("logistic.multi_delivery_team_driver5.card_id");
    public static final String MULTI_DELIVERY_TEAM_PASSWORD = getEncryptedProperty("logistic.multi_delivery_team.password");

    // UI Logistics Multi Delivery team data
    public static final String UI_MULTI_DELIVERY_TEAM_ADMIN_LOGIN = getProperty("logistic.ui_milti_delivery_admin.login");
    public static final String UI_MULTI_DELIVERY_TEAM_ADMIN_ID = getProperty("logistic.ui_milti_delivery_admin.id");
    public static final String UI_MULTI_DELIVERY_TEAM_ADMIN_CARD_ID = getProperty("logistic.ui_milti_delivery_admin.card_id");
    public static final String UI_MULTI_DELIVERY1_WMID = getProperty("logistic.ui_milti_delivery1.wmid");
    public static final String UI_MULTI_DELIVERY1_NAME = getProperty("logistic.ui_milti_delivery1.name");
    public static final String UI_MULTI_DELIVERY1_TEAM_ID = getProperty("logistic.ui_milti_delivery1.team_id");
    public static final String UI_MULTI_DELIVERY2_WMID = getProperty("logistic.ui_milti_delivery2.wmid");
    public static final String UI_MULTI_DELIVERY2_NAME = getProperty("logistic.ui_milti_delivery2.name");
    public static final String UI_MULTI_DELIVERY2_TEAM_ID = getProperty("logistic.ui_milti_delivery2.team_id");
    public static final String UI_MULTI_DELIVERY3_WMID = getProperty("logistic.ui_milti_delivery3.wmid");
    public static final String UI_MULTI_DELIVERY3_NAME = getProperty("logistic.ui_milti_delivery3.name");
    public static final String UI_MULTI_DELIVERY3_TEAM_ID = getProperty("logistic.ui_milti_delivery3.team_id");
    public static final String UI_MULTI_DELIVERY4_WMID = getProperty("logistic.ui_milti_delivery4.wmid");
    public static final String UI_MULTI_DELIVERY4_NAME = getProperty("logistic.ui_milti_delivery4.name");
    public static final String UI_MULTI_DELIVERY4_TEAM_ID = getProperty("logistic.ui_milti_delivery4.team_id");
    public static final String UI_MULTI_DELIVERY5_WMID = getProperty("logistic.ui_milti_delivery5.wmid");
    public static final String UI_MULTI_DELIVERY5_NAME = getProperty("logistic.ui_milti_delivery5.name");
    public static final String UI_MULTI_DELIVERY5_TEAM_ID = getProperty("logistic.ui_milti_delivery5.team_id");
    public static final String UI_MULTI_DELIVERY6_WMID = getProperty("logistic.ui_milti_delivery6.wmid");
    public static final String UI_MULTI_DELIVERY6_NAME = getProperty("logistic.ui_milti_delivery6.name");
    public static final String UI_MULTI_DELIVERY6_TEAM_ID = getProperty("logistic.ui_milti_delivery6.team_id");
    public static final String UI_MULTI_DELIVERY7_WMID = getProperty("logistic.ui_milti_delivery7.wmid");
    public static final String UI_MULTI_DELIVERY7_NAME = getProperty("logistic.ui_milti_delivery7.name");
    public static final String UI_MULTI_DELIVERY7_TEAM_ID = getProperty("logistic.ui_milti_delivery7.team_id");
    public static final String UI_MULTI_DELIVERY8_WMID = getProperty("logistic.ui_milti_delivery8.wmid");
    public static final String UI_MULTI_DELIVERY8_NAME = getProperty("logistic.ui_milti_delivery8.name");
    public static final String UI_MULTI_DELIVERY8_TEAM_ID = getProperty("logistic.ui_milti_delivery8.team_id");
    public static final String UI_MULTI_DELIVERY9_WMID = getProperty("logistic.ui_milti_delivery9.wmid");
    public static final String UI_MULTI_DELIVERY9_NAME = getProperty("logistic.ui_milti_delivery9.name");
    public static final String UI_MULTI_DELIVERY9_TEAM_ID = getProperty("logistic.ui_milti_delivery9.team_id");
    public static final String UI_MULTI_DELIVERY10_WMID = getProperty("logistic.ui_milti_delivery10.wmid");
    public static final String UI_MULTI_DELIVERY10_NAME = getProperty("logistic.ui_milti_delivery10.name");
    public static final String UI_MULTI_DELIVERY10_TEAM_ID = getProperty("logistic.ui_milti_delivery10.team_id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_1_LOGIN = getProperty("logistic.ui_milti_delivery_driver1.login");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_1_ID = getProperty("logistic.ui_milti_delivery_driver1.id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_1_CARD_ID = getProperty("logistic.ui_milti_delivery_driver1.card_id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_2_LOGIN = getProperty("logistic.ui_milti_delivery_driver2.login");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_2_ID = getProperty("logistic.ui_milti_delivery_driver2.id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_2_CARD_ID = getProperty("logistic.ui_milti_delivery_driver2.card_id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_3_LOGIN = getProperty("logistic.ui_milti_delivery_driver3.login");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_3_ID = getProperty("logistic.ui_milti_delivery_driver3.id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_3_CARD_ID = getProperty("logistic.ui_milti_delivery_driver3.card_id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_4_LOGIN = getProperty("logistic.ui_milti_delivery_driver4.login");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_4_ID = getProperty("logistic.ui_milti_delivery_driver4.id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_4_CARD_ID = getProperty("logistic.ui_milti_delivery_driver4.card_id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_5_LOGIN = getProperty("logistic.ui_milti_delivery_driver5.login");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_5_ID = getProperty("logistic.ui_milti_delivery_driver5.id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_5_CARD_ID = getProperty("logistic.ui_milti_delivery_driver5.card_id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_6_LOGIN = getProperty("logistic.ui_milti_delivery_driver6.login");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_6_ID = getProperty("logistic.ui_milti_delivery_driver6.id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_6_CARD_ID = getProperty("logistic.ui_milti_delivery_driver6.card_id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_7_LOGIN = getProperty("logistic.ui_milti_delivery_driver7.login");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_7_ID = getProperty("logistic.ui_milti_delivery_driver7.id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_7_CARD_ID = getProperty("logistic.ui_milti_delivery_driver7.card_id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_8_LOGIN = getProperty("logistic.ui_milti_delivery_driver8.login");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_8_ID = getProperty("logistic.ui_milti_delivery_driver8.id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_8_CARD_ID = getProperty("logistic.ui_milti_delivery_driver8.card_id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_9_LOGIN = getProperty("logistic.ui_milti_delivery_driver9.login");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_9_ID = getProperty("logistic.ui_milti_delivery_driver9.id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_9_CARD_ID = getProperty("logistic.ui_milti_delivery_driver9.card_id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_10_LOGIN = getProperty("logistic.ui_milti_delivery_driver10.login");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_10_ID = getProperty("logistic.ui_milti_delivery_driver10.id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_10_CARD_ID = getProperty("logistic.ui_milti_delivery_driver10.card_id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_11_LOGIN = getProperty("logistic.ui_milti_delivery_driver11.login");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_11_ID = getProperty("logistic.ui_milti_delivery_driver11.id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_11_CARD_ID = getProperty("logistic.ui_milti_delivery_driver11.card_id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_12_LOGIN = getProperty("logistic.ui_milti_delivery_driver12.login");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_12_ID = getProperty("logistic.ui_milti_delivery_driver12.id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_12_CARD_ID = getProperty("logistic.ui_milti_delivery_driver12.card_id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_13_LOGIN = getProperty("logistic.ui_milti_delivery_driver13.login");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_13_ID = getProperty("logistic.ui_milti_delivery_driver13.id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_13_CARD_ID = getProperty("logistic.ui_milti_delivery_driver13.card_id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_14_LOGIN = getProperty("logistic.ui_milti_delivery_driver14.login");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_14_ID = getProperty("logistic.ui_milti_delivery_driver14.id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_14_CARD_ID = getProperty("logistic.ui_milti_delivery_driver14.card_id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_15_LOGIN = getProperty("logistic.ui_milti_delivery_driver15.login");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_15_ID = getProperty("logistic.ui_milti_delivery_driver15.id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_15_CARD_ID = getProperty("logistic.ui_milti_delivery_driver15.card_id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_16_LOGIN = getProperty("logistic.ui_milti_delivery_driver16.login");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_16_ID = getProperty("logistic.ui_milti_delivery_driver16.id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_16_CARD_ID = getProperty("logistic.ui_milti_delivery_driver16.card_id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_17_LOGIN = getProperty("logistic.ui_milti_delivery_driver17.login");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_17_ID = getProperty("logistic.ui_milti_delivery_driver17.id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_17_CARD_ID = getProperty("logistic.ui_milti_delivery_driver17.card_id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_18_LOGIN = getProperty("logistic.ui_milti_delivery_driver18.login");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_18_ID = getProperty("logistic.ui_milti_delivery_driver18.id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_18_CARD_ID = getProperty("logistic.ui_milti_delivery_driver18.card_id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_19_LOGIN = getProperty("logistic.ui_milti_delivery_driver19.login");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_19_ID = getProperty("logistic.ui_milti_delivery_driver19.id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_19_CARD_ID = getProperty("logistic.ui_milti_delivery_driver19.card_id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_20_LOGIN = getProperty("logistic.ui_milti_delivery_driver20.login");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_20_ID = getProperty("logistic.ui_milti_delivery_driver20.id");
    public static final String UI_MULTI_DELIVERY_TEAM_DRIVER_20_CARD_ID = getProperty("logistic.ui_milti_delivery_driver20.card_id");

    // Single pickup team data
    public static final String SINGLE_PICKUP_WMID = getProperty("logistic.single_pickup.wmid");
    public static final String SINGLE_PICKUP_NAME = getProperty("logistic.single_pickup.name");
    public static final String SINGLE_PICKUP_TEAM_ID = getProperty("logistic.single_pickup.team_id");
    public static final String SINGLE_PICKUP_TEAM_ADMIN_LOGIN = getProperty("logistic.single_pickup_team_admin.login");
    public static final String SINGLE_PICKUP_TEAM_ADMIN_ID = getProperty("logistic.single_pickup_team_admin.id");
    public static final String SINGLE_PICKUP_TEAM_ADMIN_CARD_ID = getProperty("logistic.single_pickup_team_admin.card_id");

    // Logistics Multi Pickup team data
    public static final String MULTI_PICKUP_TEAM_ADMIN_LOGIN = getProperty("logistic.multi_pickup_team_admin.login");
    public static final String MULTI_PICKUP_TEAM_ADMIN_ID = getProperty("logistic.multi_pickup_team_admin.id");
    public static final String MULTI_PICKUP_TEAM_ADMIN_CARD_ID = getProperty("logistic.multi_pickup_team_admin.card_id");
    public static final String MULTI_PICKUP1_WMID = getProperty("logistic.multi_pickup1.wmid");
    public static final String MULTI_PICKUP1_NAME = getProperty("logistic.multi_pickup1.name");
    public static final String MULTI_PICKUP1_TEAM_ID = getProperty("logistic.multi_pickup1.team_id");
    public static final String MULTI_PICKUP2_WMID = getProperty("logistic.multi_pickup2.wmid");
    public static final String MULTI_PICKUP2_NAME = getProperty("logistic.multi_pickup2.name");
    public static final String MULTI_PICKUP2_TEAM_ID = getProperty("logistic.multi_pickup2.team_id");
    public static final String MULTI_PICKUP3_WMID = getProperty("logistic.multi_pickup3.wmid");
    public static final String MULTI_PICKUP3_NAME = getProperty("logistic.multi_pickup3.name");
    public static final String MULTI_PICKUP3_TEAM_ID = getProperty("logistic.multi_pickup3.team_id");
    public static final String MULTI_PICKUP4_WMID = getProperty("logistic.multi_pickup4.wmid");
    public static final String MULTI_PICKUP4_NAME = getProperty("logistic.multi_pickup4.name");
    public static final String MULTI_PICKUP4_TEAM_ID = getProperty("logistic.multi_pickup4.team_id");
    public static final String MULTI_PICKUP5_WMID = getProperty("logistic.multi_pickup5.wmid");
    public static final String MULTI_PICKUP5_NAME = getProperty("logistic.multi_pickup5.name");
    public static final String MULTI_PICKUP5_TEAM_ID = getProperty("logistic.multi_pickup5.team_id");

    // Logistics Delivery and Pickup team data
    public static final String DELIVERY_PICKUP_TEAM_ADMIN_LOGIN = getProperty("logistic.delivery_pickup_team_admin.login");
    public static final String DELIVERY_PICKUP_TEAM_ADMIN_ID = getProperty("logistic.delivery_pickup_team_admin.id");
    public static final String DELIVERY_PICKUP_TEAM_ADMIN_CARD_ID = getProperty("logistic.delivery_pickup_team_admin.card_id");
    public static final String DELIVERY_PICKUP_DELIVERY1_WMID = getProperty("logistic.delivery_pickup_delivery1.wmid");
    public static final String DELIVERY_PICKUP_DELIVERY1_NAME = getProperty("logistic.delivery_pickup_delivery1.name");
    public static final String DELIVERY_PICKUP_DELIVERY1_TEAM_ID = getProperty("logistic.delivery_pickup_delivery1.team_id");
    public static final String DELIVERY_PICKUP_DELIVERY2_WMID = getProperty("logistic.delivery_pickup_delivery2.wmid");
    public static final String DELIVERY_PICKUP_DELIVERY2_NAME = getProperty("logistic.delivery_pickup_delivery2.name");
    public static final String DELIVERY_PICKUP_DELIVERY2_TEAM_ID = getProperty("logistic.delivery_pickup_delivery2.team_id");
    public static final String DELIVERY_PICKUP_DELIVERY3_WMID = getProperty("logistic.delivery_pickup_delivery3.wmid");
    public static final String DELIVERY_PICKUP_DELIVERY3_NAME = getProperty("logistic.delivery_pickup_delivery3.name");
    public static final String DELIVERY_PICKUP_DELIVERY3_TEAM_ID = getProperty("logistic.delivery_pickup_delivery3.team_id");
    public static final String DELIVERY_PICKUP_DELIVERY_TEAM_DRIVER_1_LOGIN = getProperty("logistic.delivery_pickup_delivery_team_driver1.login");
    public static final String DELIVERY_PICKUP_DELIVERY_TEAM_DRIVER_1_ID = getProperty("logistic.delivery_pickup_delivery_team_driver1.id");
    public static final String DELIVERY_PICKUP_DELIVERY_TEAM_DRIVER_1_CARD_ID = getProperty("logistic.delivery_pickup_delivery_team_driver1.card_id");
    public static final String DELIVERY_PICKUP_DELIVERY_TEAM_DRIVER_2_LOGIN = getProperty("logistic.delivery_pickup_delivery_team_driver2.login");
    public static final String DELIVERY_PICKUP_DELIVERY_TEAM_DRIVER_2_ID = getProperty("logistic.delivery_pickup_delivery_team_driver2.id");
    public static final String DELIVERY_PICKUP_DELIVERY_TEAM_DRIVER_2_CARD_ID = getProperty("logistic.delivery_pickup_delivery_team_driver2.card_id");
    public static final String DELIVERY_PICKUP_DELIVERY_TEAM_DRIVER_3_LOGIN = getProperty("logistic.delivery_pickup_delivery_team_driver3.login");
    public static final String DELIVERY_PICKUP_DELIVERY_TEAM_DRIVER_3_ID = getProperty("logistic.delivery_pickup_delivery_team_driver3.id");
    public static final String DELIVERY_PICKUP_DELIVERY_TEAM_DRIVER_3_CARD_ID = getProperty("logistic.delivery_pickup_delivery_team_driver3.card_id");
    public static final String DELIVERY_PICKUP_DISPENSARY1_WMID = getProperty("logistic.delivery_pickup_dispensary1.wmid");
    public static final String DELIVERY_PICKUP_DISPENSARY1_NAME = getProperty("logistic.delivery_pickup_dispensary1.name");
    public static final String DELIVERY_PICKUP_DISPENSARY1_TEAM_ID = getProperty("logistic.delivery_pickup_dispensary1.team_id");
    public static final String DELIVERY_PICKUP_DISPENSARY2_WMID = getProperty("logistic.delivery_pickup_dispensary2.wmid");
    public static final String DELIVERY_PICKUP_DISPENSARY2_NAME = getProperty("logistic.delivery_pickup_dispensary2.name");
    public static final String DELIVERY_PICKUP_DISPENSARY2_TEAM_ID = getProperty("logistic.delivery_pickup_dispensary2.team_id");
    public static final String DELIVERY_PICKUP_DISPENSARY3_WMID = getProperty("logistic.delivery_pickup_dispensary3.wmid");
    public static final String DELIVERY_PICKUP_DISPENSARY3_NAME = getProperty("logistic.delivery_pickup_dispensary3.name");
    public static final String DELIVERY_PICKUP_DISPENSARY3_TEAM_ID = getProperty("logistic.delivery_pickup_dispensary3.team_id");

    // Logistics Single Delivery and Pickup team data
    public static final String SINGLE_DELIVERY_PICKUP_TEAM_ADMIN_LOGIN = getProperty("logistic.single_delivery_pickup_team_admin.login");
    public static final String SINGLE_DELIVERY_PICKUP_TEAM_ADMIN_ID = getProperty("logistic.single_delivery_pickup_team_admin.id");
    public static final String SINGLE_DELIVERY_PICKUP_TEAM_ADMIN_CARD_ID = getProperty("logistic.single_delivery_pickup_team_admin.card_id");
    public static final String SINGLE_DELIVERY_PICKUP_DELIVERY1_WMID = getProperty("logistic.single_delivery_pickup_delivery1.wmid");
    public static final String SINGLE_DELIVERY_PICKUP_DELIVERY1_NAME = getProperty("logistic.single_delivery_pickup_delivery1.name");
    public static final String SINGLE_DELIVERY_PICKUP_DELIVERY1_TEAM_ID = getProperty("logistic.single_delivery_pickup_delivery1.team_id");
    public static final String SINGLE_DELIVERY_PICKUP_DELIVERY_TEAM_DRIVER_1_LOGIN = getProperty("logistic.single_delivery_pickup_delivery_team_driver1.login");
    public static final String SINGLE_DELIVERY_PICKUP_DELIVERY_TEAM_DRIVER_1_ID = getProperty("logistic.single_delivery_pickup_delivery_team_driver1.id");
    public static final String SINGLE_DELIVERY_PICKUP_DELIVERY_TEAM_DRIVER_1_CARD_ID = getProperty("logistic.single_delivery_pickup_delivery_team_driver1.card_id");
    public static final String SINGLE_DELIVERY_PICKUP_DISPENSARY1_WMID = getProperty("logistic.single_delivery_pickup_dispensary1.wmid");
    public static final String SINGLE_DELIVERY_PICKUP_DISPENSARY1_NAME = getProperty("logistic.single_delivery_pickup_dispensary1.name");
    public static final String SINGLE_DELIVERY_PICKUP_DISPENSARY1_TEAM_ID = getProperty("logistic.single_delivery_pickup_dispensary1.team_id");

    // Logistics Delivery and Pickup for ADD/DELETE Team Members team data
    public static final String DELIVERY_PICKUP_TEAM_MEMBER_ADMIN_LOGIN = getProperty("logistic.delivery_pickup_team_member_admin.login");
    public static final String DELIVERY_PICKUP_TEAM_MEMBER_ADMIN_ID = getProperty("logistic.delivery_pickup_team_member_admin.id");
    public static final String DELIVERY_PICKUP_TEAM_MEMBER_ADMIN_CARD_ID = getProperty("logistic.delivery_pickup_team_member_admin.card_id");
    public static final String DELIVERY_PICKUP_TEAM_MEMBER_DELIVERY1_WMID = getProperty("logistic.delivery_pickup_team_member_delivery1.wmid");
    public static final String DELIVERY_PICKUP_TEAM_MEMBER_DELIVERY1_NAME = getProperty("logistic.delivery_pickup_team_member_delivery1.name");
    public static final String DELIVERY_PICKUP_TEAM_MEMBER_DELIVERY1_TEAM_ID = getProperty("logistic.delivery_pickup_team_member_delivery1.team_id");
    public static final String DELIVERY_PICKUP_TEAM_MEMBER_DELIVERY2_WMID = getProperty("logistic.delivery_pickup_team_member_delivery2.wmid");
    public static final String DELIVERY_PICKUP_TEAM_MEMBER_DELIVERY2_NAME = getProperty("logistic.delivery_pickup_team_member_delivery2.name");
    public static final String DELIVERY_PICKUP_TEAM_MEMBER_DELIVERY2_TEAM_ID = getProperty("logistic.delivery_pickup_team_member_delivery2.team_id");
    public static final String DELIVERY_PICKUP_TEAM_MEMBER_DELIVERY_TEAM_DRIVER_1_LOGIN = getProperty("logistic.delivery_pickup_team_member_delivery_team_driver1.login");
    public static final String DELIVERY_PICKUP_TEAM_MEMBER_DELIVERY_TEAM_DRIVER_1_ID = getProperty("logistic.delivery_pickup_team_member_delivery_team_driver1.id");
    public static final String DELIVERY_PICKUP_TEAM_MEMBER_DELIVERY_TEAM_DRIVER_1_CARD_ID = getProperty("logistic.delivery_pickup_team_member_delivery_team_driver1.card_id");
    public static final String DELIVERY_PICKUP_TEAM_MEMBER_DELIVERY_TEAM_DRIVER_2_LOGIN = getProperty("logistic.delivery_pickup_team_member_delivery_team_driver2.login");
    public static final String DELIVERY_PICKUP_TEAM_MEMBER_DELIVERY_TEAM_DRIVER_2_ID = getProperty("logistic.delivery_pickup_team_member_delivery_team_driver2.id");
    public static final String DELIVERY_PICKUP_TEAM_MEMBER_DELIVERY_TEAM_DRIVER_2_CARD_ID = getProperty("logistic.delivery_pickup_team_member_delivery_team_driver2.card_id");
    public static final String DELIVERY_PICKUP_TEAM_MEMBER_DISPENSARY1_WMID = getProperty("logistic.delivery_pickup_team_member_dispensary1.wmid");
    public static final String DELIVERY_PICKUP_TEAM_MEMBER_DISPENSARY1_NAME = getProperty("logistic.delivery_pickup_team_member_dispensary1.name");
    public static final String DELIVERY_PICKUP_TEAM_MEMBER_DISPENSARY1_TEAM_ID = getProperty("logistic.delivery_pickup_team_member_dispensary1.team_id");

    // Logistics Single Delivery for ADD/DELETE Team Members team data
    public static final String SINGLE_DELIVERY_TEAM_MEMBER_ADMIN_LOGIN = getProperty("logistic.single_delivery_team_member_admin.login");
    public static final String SINGLE_DELIVERY_TEAM_MEMBER_ADMIN_ID = getProperty("logistic.single_delivery_team_member_admin.id");
    public static final String SINGLE_DELIVERY_TEAM_MEMBER_ADMIN_CARD_ID = getProperty("logistic.single_delivery_team_member_admin.card_id");
    public static final String SINGLE_DELIVERY_TEAM_MEMBER_DELIVERY1_WMID = getProperty("logistic.single_delivery_team_member_delivery1.wmid");
    public static final String SINGLE_DELIVERY_TEAM_MEMBER_DELIVERY1_NAME = getProperty("logistic.single_delivery_team_member_delivery1.name");
    public static final String SINGLE_DELIVERY_TEAM_MEMBER_DELIVERY1_TEAM_ID = getProperty("logistic.single_delivery_team_member_delivery1.team_id");
    public static final String SINGLE_DELIVERY_TEAM_MEMBER_DELIVERY_TEAM_DRIVER_1_LOGIN = getProperty("logistic.single_delivery_team_member_delivery_team_driver1.login");
    public static final String SINGLE_DELIVERY_TEAM_MEMBER_DELIVERY_TEAM_DRIVER_1_ID = getProperty("logistic.single_delivery_team_member_delivery_team_driver1.id");
    public static final String SINGLE_DELIVERY_TEAM_MEMBER_DELIVERY_TEAM_DRIVER_1_CARD_ID = getProperty("logistic.single_delivery_team_member_delivery_team_driver1.card_id");

    // Logistics Multi Delivery for ADD/DELETE Team Members team data
    public static final String MULTI_DELIVERY_TEAM_MEMBER_ADMIN_LOGIN = getProperty("logistic.multi_delivery_team_member_admin.login");
    public static final String MULTI_DELIVERY_TEAM_MEMBER_ADMIN_ID = getProperty("logistic.multi_delivery_team_member_admin.id");
    public static final String MULTI_DELIVERY_TEAM_MEMBER_ADMIN_CARD_ID = getProperty("logistic.multi_delivery_team_member_admin.card_id");
    public static final String MULTI_DELIVERY_TEAM_MEMBER_DELIVERY1_WMID = getProperty("logistic.multi_delivery_team_member_delivery1.wmid");
    public static final String MULTI_DELIVERY_TEAM_MEMBER_DELIVERY1_NAME = getProperty("logistic.multi_delivery_team_member_delivery1.name");
    public static final String MULTI_DELIVERY_TEAM_MEMBER_DELIVERY1_TEAM_ID = getProperty("logistic.multi_delivery_team_member_delivery1.team_id");
    public static final String MULTI_DELIVERY_TEAM_MEMBER_DELIVERY2_WMID = getProperty("logistic.multi_delivery_team_member_delivery2.wmid");
    public static final String MULTI_DELIVERY_TEAM_MEMBER_DELIVERY2_NAME = getProperty("logistic.multi_delivery_team_member_delivery2.name");
    public static final String MULTI_DELIVERY_TEAM_MEMBER_DELIVERY2_TEAM_ID = getProperty("logistic.multi_delivery_team_member_delivery2.team_id");
    public static final String MULTI_DELIVERY_TEAM_MEMBER_DELIVERY_TEAM_DRIVER_1_LOGIN = getProperty("logistic.multi_delivery_team_member_delivery_team_driver1.login");
    public static final String MULTI_DELIVERY_TEAM_MEMBER_DELIVERY_TEAM_DRIVER_1_ID = getProperty("logistic.multi_delivery_team_member_delivery_team_driver1.id");
    public static final String MULTI_DELIVERY_TEAM_MEMBER_DELIVERY_TEAM_DRIVER_1_CARD_ID = getProperty("logistic.multi_delivery_team_member_delivery_team_driver1.card_id");

    // Logistics organization for different actions
    public static final String ORG_FOR_DIFFERENT_ACTIONS_NAME = getProperty("logistic.org.for_different_actions.name");
    public static final String ORG_FOR_DIFFERENT_ACTIONS_OWNER_NAME = LOGISTIC_USER_FOR_DIFFERENT_ROLES_LOGIN;
    public static final String ORG_FOR_DIFFERENT_ACTIONS_OWNER_PASSWORD = LOGISTIC_USER_FOR_DIFFERENT_ROLES_PASSWORD;
    public static final String ORG_FOR_DIFFERENT_ACTIONS_OWNER_USER_ID = LOGISTIC_USER_FOR_DIFFERENT_ROLES_ID;

    // Logistics organization without DCs
    public static final String ORG_WITHOUT_DCS_NAME = getProperty("logistic.org.without_dcs.name");
    public static final String ORG_WITHOUT_DCS_ID = getProperty("logistic.org.without_dcs.id");
    public static final String ORG_WITHOUT_DCS_PICKUP_NAME = getProperty("logistic.org.without_dcs.pickup.name");
    public static final String ORG_WITHOUT_DCS_PICKUP_WMID = getProperty("logistic.org.without_dcs.pickup.wmid");
    public static final String ORG_WITHOUT_DCS_OWNER_NAME = getProperty("logistic.org.without_dcs.owner_name");
    public static final String ORG_WITHOUT_DCS_OWNER_USER_ID = getProperty("logistic.org.without_dcs.owner_user_id");
    public static final String ORG_WITHOUT_DCS_OWNER_CARD_ID = getProperty("logistic.org.without_dcs.owner_card_id");
    public static final String ORG_WITHOUT_DCS_DISPATCHER_NAME = getProperty("logistic.org.without_dcs.dispatcher_name");
    public static final String ORG_WITHOUT_DCS_DISPATCHER_USER_ID = getProperty("logistic.org.without_dcs.dispatcher_user_id");
    public static final String ORG_WITHOUT_DCS_DISPATCHER_CARD_ID = getProperty("logistic.org.without_dcs.dispatcher_card_id");
    public static final String ORG_WITHOUT_DCS_ADMIN_NAME = getProperty("logistic.org.without_dcs.admin_name");
    public static final String ORG_WITHOUT_DCS_ADMIN_USER_ID = getProperty("logistic.org.without_dcs.admin_user_id");
    public static final String ORG_WITHOUT_DCS_ADMIN_CARD_ID = getProperty("logistic.org.without_dcs.admin_card_id");

    // Logistics organization for add delete members
    public static final String ORG_FOR_ADD_DELETE_MEMBERS_NAME = getProperty("logistic.org_for_add_delete_members.name");
    public static final String ORG_FOR_ADD_DELETE_MEMBERS_ID = getProperty("logistic.org_for_add_delete_members.id");
    public static final String ORG_FOR_ADD_DELETE_MEMBERS_DC_NAME = getProperty("logistic.org_for_add_delete_members.dc_name");
    public static final String ORG_FOR_ADD_DELETE_MEMBERS_DC_ID = getProperty("logistic.org_for_add_delete_members.dc_id");
    public static final String ORG_FOR_ADD_DELETE_MEMBERS_DELIVERY_NAME = getProperty("logistic.org_for_add_delete_members.delivery_name");
    public static final String ORG_FOR_ADD_DELETE_MEMBERS_DELIVERY_WMID = getProperty("logistic.org_for_add_delete_members.delivery_wmid");
    public static final String ORG_FOR_ADD_DELETE_MEMBERS_OWNER_NAME = getProperty("logistic.org_for_add_delete_members.owner_name");
    public static final String ORG_FOR_ADD_DELETE_MEMBERS_OWNER_USER_ID = getProperty("logistic.org_for_add_delete_members.owner_user_id");
    public static final String ORG_FOR_ADD_DELETE_MEMBERS_OWNER_CARD_ID = getProperty("logistic.org_for_add_delete_members.owner_card_id");
    public static final String ORG_FOR_ADD_DELETE_MEMBERS_DISPATCHER_NAME = getProperty("logistic.org_for_add_delete_members.dispatcher_name");
    public static final String ORG_FOR_ADD_DELETE_MEMBERS_DISPATCHER_USER_ID = getProperty("logistic.org_for_add_delete_members.dispatcher_user_id");
    public static final String ORG_FOR_ADD_DELETE_MEMBERS_DISPATCHER_CARD_ID = getProperty("logistic.org_for_add_delete_members.dispatcher_card_id");
    public static final String ORG_FOR_ADD_DELETE_MEMBERS_ADMIN_NAME = getProperty("logistic.org_for_add_delete_members.admin_name");
    public static final String ORG_FOR_ADD_DELETE_MEMBERS_ADMIN_USER_ID = getProperty("logistic.org_for_add_delete_members.admin_user_id");
    public static final String ORG_FOR_ADD_DELETE_MEMBERS_ADMIN_CARD_ID = getProperty("logistic.org_for_add_delete_members.admin_card_id");

    // Logistics organization with single pickup data
    public static final String ORG_WITH_SINGLE_PICKUP_NAME = getProperty("logistic.org.with_single_pickup.name");
    public static final String ORG_WITH_SINGLE_PICKUP_ID = getProperty("logistic.org.with_single_pickup.id");
    public static final String ORG_DC_WITH_SINGLE_PICKUP_NAME = getProperty("logistic.org_dc.with_single_pickup.name");
    public static final String ORG_DC_WITH_SINGLE_PICKUP_ID = getProperty("logistic.org_dc.with_single_pickup.id");
    public static final String ORG_SINGLE_PICKUP_NAME = getProperty("logistic.org.single_pickup.name");
    public static final String ORG_SINGLE_PICKUP_WMID = getProperty("logistic.org.single_pickup.wmid");
    public static final String ORG_WITH_SINGLE_PICKUP_OWNER_NAME = getProperty("logistic.org.with_single_pickup.owner_name");
    public static final String ORG_WITH_SINGLE_PICKUP_OWNER_USER_ID = getProperty("logistic.org.with_single_pickup.owner_user_id");
    public static final String ORG_WITH_SINGLE_PICKUP_OWNER_CARD_ID = getProperty("logistic.org.with_single_pickup.owner_card_id");
    public static final String ORG_WITH_SINGLE_PICKUP_DISPATCHER_NAME = getProperty("logistic.org.with_single_pickup.dispatcher_name");
    public static final String ORG_WITH_SINGLE_PICKUP_DISPATCHER_USER_ID = getProperty("logistic.org.with_single_pickup.dispatcher_user_id");
    public static final String ORG_WITH_SINGLE_PICKUP_DISPATCHER_CARD_ID = getProperty("logistic.org.with_single_pickup.dispatcher_card_id");
    public static final String ORG_WITH_SINGLE_PICKUP_ADMIN_NAME = getProperty("logistic.org.with_single_pickup.admin_name");
    public static final String ORG_WITH_SINGLE_PICKUP_ADMIN_USER_ID = getProperty("logistic.org.with_single_pickup.admin_user_id");
    public static final String ORG_WITH_SINGLE_PICKUP_ADMIN_CARD_ID = getProperty("logistic.org.with_single_pickup.admin_card_id");

    // Logistics organization with single pickup data for Online Ordering
    public static final String OOS_ORG_WITH_SINGLE_PICKUP_NAME = getProperty("oos.org.with_single_pickup.name");
    public static final String OOS_ORG_WITH_SINGLE_PICKUP_ID = getProperty("oos.org.with_single_pickup.id");
    public static final String OOS_ORG_DC_WITH_SINGLE_PICKUP_NAME = getProperty("oos.org_dc.with_single_pickup.name");
    public static final String OOS_ORG_DC_WITH_SINGLE_PICKUP_ID = getProperty("oos.org_dc.with_single_pickup.id");
    public static final String OOS_ORG_SINGLE_PICKUP_NAME = getProperty("oos.org.single_pickup.name");
    public static final String OOS_ORG_SINGLE_PICKUP_WMID = getProperty("oos.org.single_pickup.wmid");
    public static final String OOS_ORG_WITH_SINGLE_PICKUP_OWNER_NAME = getProperty("oos.org.with_single_pickup.owner_name");
    public static final String OOS_ORG_WITH_SINGLE_PICKUP_OWNER_USER_ID = getProperty("oos.org.with_single_pickup.owner_user_id");
    public static final String OOS_ORG_WITH_SINGLE_PICKUP_OWNER_CARD_ID = getProperty("oos.org.with_single_pickup.owner_card_id");
    public static final String OOS_ORG_WITH_SINGLE_PICKUP_DISPATCHER_NAME = getProperty("oos.org.with_single_pickup.dispatcher_name");
    public static final String OOS_ORG_WITH_SINGLE_PICKUP_DISPATCHER_USER_ID = getProperty("oos.org.with_single_pickup.dispatcher_user_id");
    public static final String OOS_ORG_WITH_SINGLE_PICKUP_DISPATCHER_CARD_ID = getProperty("oos.org.with_single_pickup.dispatcher_card_id");
    public static final String OOS_ORG_WITH_SINGLE_PICKUP_ADMIN_NAME = getProperty("oos.org.with_single_pickup.admin_name");
    public static final String OOS_ORG_WITH_SINGLE_PICKUP_ADMIN_USER_ID = getProperty("oos.org.with_single_pickup.admin_user_id");
    public static final String OOS_ORG_WITH_SINGLE_PICKUP_ADMIN_CARD_ID = getProperty("oos.org.with_single_pickup.admin_card_id");
    public static final String OOS_ORG_WITH_SINGLE_PICKUP_WM_STORE_URL = getProperty("oos.org.with_single_pickup.wm_store_url");

    // Logistics organization with single delivery data
    public static final String ORG_WITH_SINGLE_DELIVERY_NAME = getProperty("logistic.org.with_single_delivery.name");
    public static final String ORG_WITH_SINGLE_DELIVERY_ID = getProperty("logistic.org.with_single_delivery.id");
    public static final String ORG_DC_WITH_SINGLE_DELIVERY_NAME = getProperty("logistic.org_dc.with_single_delivery.name");
    public static final String ORG_DC_WITH_SINGLE_DELIVERY_ID = getProperty("logistic.org_dc.with_single_delivery.id");
    public static final String ORG_SINGLE_DELIVERY_NAME = getProperty("logistic.org.single_delivery.name");
    public static final String ORG_SINGLE_DELIVERY_WMID = getProperty("logistic.org.single_delivery.wmid");
    public static final String ORG_WITH_SINGLE_DELIVERY_OWNER_NAME = getProperty("logistic.org.with_single_delivery.owner_name");
    public static final String ORG_WITH_SINGLE_DELIVERY_OWNER_PASSWORD = DEFAULT_PASSWORD;
    public static final String ORG_WITH_SINGLE_DELIVERY_OWNER_USER_ID = getProperty("logistic.org.with_single_delivery.owner_user_id");
    public static final String ORG_WITH_SINGLE_DELIVERY_OWNER_CARD_ID = getProperty("logistic.org.with_single_delivery.owner_card_id");
    public static final String ORG_WITH_SINGLE_DELIVERY_DISPATCHER_NAME = getProperty("logistic.org.with_single_delivery.dispatcher_name");
    public static final String ORG_WITH_SINGLE_DELIVERY_DISPATCHER_PASSWORD = DEFAULT_PASSWORD;
    public static final String ORG_WITH_SINGLE_DELIVERY_DISPATCHER_USER_ID = getProperty("logistic.org.with_single_delivery.dispatcher_user_id");
    public static final String ORG_WITH_SINGLE_DELIVERY_DISPATCHER_CARD_ID = getProperty("logistic.org.with_single_delivery.dispatcher_card_id");
    public static final String ORG_WITH_SINGLE_DELIVERY_ADMIN_NAME = getProperty("logistic.org.with_single_delivery.admin_name");
    public static final String ORG_WITH_SINGLE_DELIVERY_ADMIN_PASSWORD = DEFAULT_PASSWORD;
    public static final String ORG_WITH_SINGLE_DELIVERY_ADMIN_USER_ID = getProperty("logistic.org.with_single_delivery.admin_user_id");
    public static final String ORG_WITH_SINGLE_DELIVERY_ADMIN_CARD_ID = getProperty("logistic.org.with_single_delivery.admin_card_id");
    public static final String ORG_WITH_SINGLE_DELIVERY_DRIVER1_NAME = getProperty("logistic.org.with_single_delivery_driver1.login");
    public static final String ORG_WITH_SINGLE_DELIVERY_DRIVER1_USER_ID = getProperty("logistic.org.with_single_delivery_driver1.user_id");
    public static final String ORG_WITH_SINGLE_DELIVERY_DRIVER1_CARD_ID = getProperty("logistic.org.with_single_delivery_driver1.card_id");
    public static final String ORG_WITH_SINGLE_DELIVERY_DRIVER2_NAME = getProperty("logistic.org.with_single_delivery_driver2.login");
    public static final String ORG_WITH_SINGLE_DELIVERY_DRIVER2_USER_ID = getProperty("logistic.org.with_single_delivery_driver2.user_id");
    public static final String ORG_WITH_SINGLE_DELIVERY_DRIVER2_CARD_ID = getProperty("logistic.org.with_single_delivery_driver2.card_id");
    public static final String ORG_WITH_SINGLE_DELIVERY_DRIVER3_NAME = getProperty("logistic.org.with_single_delivery_driver3.login");
    public static final String ORG_WITH_SINGLE_DELIVERY_DRIVER3_USER_ID = getProperty("logistic.org.with_single_delivery_driver3.user_id");
    public static final String ORG_WITH_SINGLE_DELIVERY_DRIVER3_CARD_ID = getProperty("logistic.org.with_single_delivery_driver3.card_id");
    public static final String ORG_WITH_SINGLE_DELIVERY_DRIVER4_NAME = getProperty("logistic.org.with_single_delivery_driver4.login");
    public static final String ORG_WITH_SINGLE_DELIVERY_DRIVER4_USER_ID = getProperty("logistic.org.with_single_delivery_driver4.user_id");
    public static final String ORG_WITH_SINGLE_DELIVERY_DRIVER4_CARD_ID = getProperty("logistic.org.with_single_delivery_driver4.card_id");
    public static final String ORG_WITH_SINGLE_DELIVERY_DRIVER5_NAME = getProperty("logistic.org.with_single_delivery_driver5.login");
    public static final String ORG_WITH_SINGLE_DELIVERY_DRIVER5_USER_ID = getProperty("logistic.org.with_single_delivery_driver5.user_id");
    public static final String ORG_WITH_SINGLE_DELIVERY_DRIVER5_CARD_ID = getProperty("logistic.org.with_single_delivery_driver5.card_id");

    // Logistics organization with single delivery data for Online Ordering
    public static final String OOS_ORG_WITH_SINGLE_DELIVERY_NAME = getProperty("oos.org.with_single_delivery.name");
    public static final String OOS_ORG_WITH_SINGLE_DELIVERY_ID = getProperty("oos.org.with_single_delivery.id");
    public static final String OOS_ORG_DC_WITH_SINGLE_DELIVERY_NAME = getProperty("oos.org_dc.with_single_delivery.name");
    public static final String OOS_ORG_DC_WITH_SINGLE_DELIVERY_ID = getProperty("oos.org_dc.with_single_delivery.id");
    public static final String OOS_ORG_SINGLE_DELIVERY_NAME = getProperty("oos.org.single_delivery.name");
    public static final String OOS_ORG_SINGLE_DELIVERY_WMID = getProperty("oos.org.single_delivery.wmid");
    public static final String OOS_ORG_WITH_SINGLE_DELIVERY_OWNER_NAME = getProperty("oos.org.with_single_delivery.owner_name");
    public static final String OOS_ORG_WITH_SINGLE_DELIVERY_OWNER_PASSWORD = DEFAULT_PASSWORD;
    public static final String OOS_ORG_WITH_SINGLE_DELIVERY_OWNER_USER_ID = getProperty("oos.org.with_single_delivery.owner_user_id");
    public static final String OOS_ORG_WITH_SINGLE_DELIVERY_OWNER_CARD_ID = getProperty("oos.org.with_single_delivery.owner_card_id");
    public static final String OOS_ORG_WITH_SINGLE_DELIVERY_DISPATCHER_NAME = getProperty("oos.org.with_single_delivery.dispatcher_name");
    public static final String OOS_ORG_WITH_SINGLE_DELIVERY_DISPATCHER_PASSWORD = DEFAULT_PASSWORD;
    public static final String OOS_ORG_WITH_SINGLE_DELIVERY_DISPATCHER_USER_ID = getProperty("oos.org.with_single_delivery.dispatcher_user_id");
    public static final String OOS_ORG_WITH_SINGLE_DELIVERY_DISPATCHER_CARD_ID = getProperty("oos.org.with_single_delivery.dispatcher_card_id");
    public static final String OOS_ORG_WITH_SINGLE_DELIVERY_ADMIN_NAME = getProperty("oos.org.with_single_delivery.admin_name");
    public static final String OOS_ORG_WITH_SINGLE_DELIVERY_ADMIN_PASSWORD = DEFAULT_PASSWORD;
    public static final String OOS_ORG_WITH_SINGLE_DELIVERY_ADMIN_USER_ID = getProperty("oos.org.with_single_delivery.admin_user_id");
    public static final String OOS_ORG_WITH_SINGLE_DELIVERY_ADMIN_CARD_ID = getProperty("oos.org.with_single_delivery.admin_card_id");
    public static final String OOS_ORG_WITH_SINGLE_DELIVERY_DRIVER1_NAME = getProperty("oos.org.with_single_delivery_driver1.login");
    public static final String OOS_ORG_WITH_SINGLE_DELIVERY_DRIVER1_USER_ID = getProperty("oos.org.with_single_delivery_driver1.user_id");
    public static final String OOS_ORG_WITH_SINGLE_DELIVERY_DRIVER1_CARD_ID = getProperty("oos.org.with_single_delivery_driver1.card_id");

    // Logistics organization with multi delivery data for Online Ordering
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_NAME = getProperty("oos.org.with_multi_delivery.name");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_ID = getProperty("oos.org.with_multi_delivery.id");
    public static final String OOS_ORG_DC_WITH_MULTI_DELIVERY_DC1_NAME = getProperty("oos.org_dc.with_multi_delivery.dc1.name");
    public static final String OOS_ORG_DC_WITH_MULTI_DELIVERY_DC1_ID = getProperty("oos.org_dc.with_multi_delivery.dc1.id");
    public static final String OOS_ORG_DC_WITH_MULTI_DELIVERY_DC2_NAME = getProperty("oos.org_dc.with_multi_delivery.dc2.name");
    public static final String OOS_ORG_DC_WITH_MULTI_DELIVERY_DC2_ID = getProperty("oos.org_dc.with_multi_delivery.dc2.id");
    public static final String OOS_ORG_DC_WITH_MULTI_DELIVERY_DC3_NAME = getProperty("oos.org_dc.with_multi_delivery.dc3.name");
    public static final String OOS_ORG_DC_WITH_MULTI_DELIVERY_DC3_ID = getProperty("oos.org_dc.with_multi_delivery.dc3.id");
    public static final String OOS_ORG_DC_WITH_MULTI_DELIVERY_DC4_NAME = getProperty("oos.org_dc.with_multi_delivery.dc4.name");
    public static final String OOS_ORG_DC_WITH_MULTI_DELIVERY_DC4_ID = getProperty("oos.org_dc.with_multi_delivery.dc4.id");
    public static final String OOS_ORG_DC_WITH_MULTI_DELIVERY_DC5_NAME = getProperty("oos.org_dc.with_multi_delivery.dc5.name");
    public static final String OOS_ORG_DC_WITH_MULTI_DELIVERY_DC5_ID = getProperty("oos.org_dc.with_multi_delivery.dc5.id");
    public static final String OOS_ORG_DC_WITH_MULTI_DELIVERY_DC6_NAME = getProperty("oos.org_dc.with_multi_delivery.dc6.name");
    public static final String OOS_ORG_DC_WITH_MULTI_DELIVERY_DC6_ID = getProperty("oos.org_dc.with_multi_delivery.dc6.id");
    public static final String OOS_ORG_DC_WITH_MULTI_DELIVERY_DC7_NAME = getProperty("oos.org_dc.with_multi_delivery.dc7.name");
    public static final String OOS_ORG_DC_WITH_MULTI_DELIVERY_DC7_ID = getProperty("oos.org_dc.with_multi_delivery.dc7.id");
    public static final String OOS_ORG_DC_WITH_MULTI_DELIVERY_DC8_NAME = getProperty("oos.org_dc.with_multi_delivery.dc8.name");
    public static final String OOS_ORG_DC_WITH_MULTI_DELIVERY_DC8_ID = getProperty("oos.org_dc.with_multi_delivery.dc8.id");
    public static final String OOS_ORG_DC_WITH_MULTI_DELIVERY_DC9_NAME = getProperty("oos.org_dc.with_multi_delivery.dc9.name");
    public static final String OOS_ORG_DC_WITH_MULTI_DELIVERY_DC9_ID = getProperty("oos.org_dc.with_multi_delivery.dc9.id");
    public static final String OOS_ORG_DC_WITH_MULTI_DELIVERY_DC10_NAME = getProperty("oos.org_dc.with_multi_delivery.dc10.name");
    public static final String OOS_ORG_DC_WITH_MULTI_DELIVERY_DC10_ID = getProperty("oos.org_dc.with_multi_delivery.dc10.id");
    public static final String OOS_ORG_MULTI_DELIVERY1_NAME = getProperty("oos.org.multi_delivery1.name");
    public static final String OOS_ORG_MULTI_DELIVERY1_WMID = getProperty("oos.org.multi_delivery1.wmid");
    public static final String OOS_ORG_MULTI_DELIVERY2_NAME = getProperty("oos.org.multi_delivery2.name");
    public static final String OOS_ORG_MULTI_DELIVERY2_WMID = getProperty("oos.org.multi_delivery2.wmid");
    public static final String OOS_ORG_MULTI_DELIVERY3_NAME = getProperty("oos.org.multi_delivery3.name");
    public static final String OOS_ORG_MULTI_DELIVERY3_WMID = getProperty("oos.org.multi_delivery3.wmid");
    public static final String OOS_ORG_MULTI_DELIVERY4_NAME = getProperty("oos.org.multi_delivery4.name");
    public static final String OOS_ORG_MULTI_DELIVERY4_WMID = getProperty("oos.org.multi_delivery4.wmid");
    public static final String OOS_ORG_MULTI_DELIVERY5_NAME = getProperty("oos.org.multi_delivery5.name");
    public static final String OOS_ORG_MULTI_DELIVERY5_WMID = getProperty("oos.org.multi_delivery5.wmid");
    public static final String OOS_ORG_MULTI_DELIVERY6_NAME = getProperty("oos.org.multi_delivery6.name");
    public static final String OOS_ORG_MULTI_DELIVERY6_WMID = getProperty("oos.org.multi_delivery6.wmid");
    public static final String OOS_ORG_MULTI_DELIVERY7_NAME = getProperty("oos.org.multi_delivery7.name");
    public static final String OOS_ORG_MULTI_DELIVERY7_WMID = getProperty("oos.org.multi_delivery7.wmid");
    public static final String OOS_ORG_MULTI_DELIVERY8_NAME = getProperty("oos.org.multi_delivery8.name");
    public static final String OOS_ORG_MULTI_DELIVERY8_WMID = getProperty("oos.org.multi_delivery8.wmid");
    public static final String OOS_ORG_MULTI_DELIVERY9_NAME = getProperty("oos.org.multi_delivery9.name");
    public static final String OOS_ORG_MULTI_DELIVERY9_WMID = getProperty("oos.org.multi_delivery9.wmid");
    public static final String OOS_ORG_MULTI_DELIVERY10_NAME = getProperty("oos.org.multi_delivery10.name");
    public static final String OOS_ORG_MULTI_DELIVERY10_WMID = getProperty("oos.org.multi_delivery10.wmid");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_OWNER_NAME = getProperty("oos.org.with_multi_delivery.owner_name");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_OWNER_PASSWORD = DEFAULT_PASSWORD;
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_OWNER_USER_ID = getProperty("oos.org.with_multi_delivery.owner_user_id");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_OWNER_CARD_ID = getProperty("oos.org.with_multi_delivery.owner_card_id");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_DISPATCHER_NAME = getProperty("oos.org.with_multi_delivery.dispatcher_name");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_DISPATCHER_PASSWORD = DEFAULT_PASSWORD;
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_DISPATCHER_USER_ID = getProperty("oos.org.with_multi_delivery.dispatcher_user_id");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_DISPATCHER_CARD_ID = getProperty("oos.org.with_multi_delivery.dispatcher_card_id");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_ADMIN_NAME = getProperty("oos.org.with_multi_delivery.admin_name");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_ADMIN_PASSWORD = DEFAULT_PASSWORD;
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_ADMIN_USER_ID = getProperty("oos.org.with_multi_delivery.admin_user_id");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_ADMIN_CARD_ID = getProperty("oos.org.with_multi_delivery.admin_card_id");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_DRIVER1_NAME = getProperty("oos.org.with_multi_delivery_driver1.login");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_DRIVER1_USER_ID = getProperty("oos.org.with_multi_delivery_driver1.user_id");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_DRIVER1_CARD_ID = getProperty("oos.org.with_multi_delivery_driver1.card_id");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_DRIVER2_NAME = getProperty("oos.org.with_multi_delivery_driver2.login");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_DRIVER2_USER_ID = getProperty("oos.org.with_multi_delivery_driver2.user_id");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_DRIVER2_CARD_ID = getProperty("oos.org.with_multi_delivery_driver2.card_id");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_DRIVER3_NAME = getProperty("oos.org.with_multi_delivery_driver3.login");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_DRIVER3_USER_ID = getProperty("oos.org.with_multi_delivery_driver3.user_id");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_DRIVER3_CARD_ID = getProperty("oos.org.with_multi_delivery_driver3.card_id");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_DRIVER4_NAME = getProperty("oos.org.with_multi_delivery_driver4.login");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_DRIVER4_USER_ID = getProperty("oos.org.with_multi_delivery_driver4.user_id");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_DRIVER4_CARD_ID = getProperty("oos.org.with_multi_delivery_driver4.card_id");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_DRIVER5_NAME = getProperty("oos.org.with_multi_delivery_driver5.login");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_DRIVER5_USER_ID = getProperty("oos.org.with_multi_delivery_driver5.user_id");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_DRIVER5_CARD_ID = getProperty("oos.org.with_multi_delivery_driver5.card_id");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_DRIVER6_NAME = getProperty("oos.org.with_multi_delivery_driver6.login");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_DRIVER6_USER_ID = getProperty("oos.org.with_multi_delivery_driver6.user_id");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_DRIVER6_CARD_ID = getProperty("oos.org.with_multi_delivery_driver6.card_id");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_DRIVER7_NAME = getProperty("oos.org.with_multi_delivery_driver7.login");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_DRIVER7_USER_ID = getProperty("oos.org.with_multi_delivery_driver7.user_id");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_DRIVER7_CARD_ID = getProperty("oos.org.with_multi_delivery_driver7.card_id");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_DRIVER8_NAME = getProperty("oos.org.with_multi_delivery_driver8.login");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_DRIVER8_USER_ID = getProperty("oos.org.with_multi_delivery_driver8.user_id");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_DRIVER8_CARD_ID = getProperty("oos.org.with_multi_delivery_driver8.card_id");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_DRIVER9_NAME = getProperty("oos.org.with_multi_delivery_driver9.login");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_DRIVER9_USER_ID = getProperty("oos.org.with_multi_delivery_driver9.user_id");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_DRIVER9_CARD_ID = getProperty("oos.org.with_multi_delivery_driver9.card_id");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_DRIVER10_NAME = getProperty("oos.org.with_multi_delivery_driver10.login");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_DRIVER10_USER_ID = getProperty("oos.org.with_multi_delivery_driver10.user_id");
    public static final String OOS_ORG_WITH_MULTI_DELIVERY_DRIVER10_CARD_ID = getProperty("oos.org.with_multi_delivery_driver10.card_id");

    // Logistics organization for Online Ordering
    public static final String ORDERING_ORG_NAME = getProperty("ordering.org.name");
    public static final String ORDERING_ORG_ID = getProperty("ordering.org.id");
    public static final String ORDERING_ORG_OWNER_NAME = getProperty("ordering.org.owner_name");
    public static final String ORDERING_ORG_OWNER_PASSWORD = DEFAULT_PASSWORD;
    public static final String ORDERING_ORG_OWNER_USER_ID = getProperty("ordering.org.owner_user_id");
    public static final String ORDERING_ORG_OWNER_CARD_ID = getProperty("ordering.org.owner_card_id");
    public static final String ORDERING_ORG_ADMIN_NAME = getProperty("ordering.org.admin_name");
    public static final String ORDERING_ORG_ADMIN_PASSWORD = DEFAULT_PASSWORD;
    public static final String ORDERING_ORG_ADMIN_USER_ID = getProperty("ordering.org.admin_user_id");
    public static final String ORDERING_ORG_ADMIN_CARD_ID = getProperty("ordering.org.admin_card_id");

    // Logistics organization with two delivery DC and two pickup DC data
    public static final String MULTI_ORG_NAME = getProperty("logistic.multi_org.name");
    public static final String MULTI_ORG_ID = getProperty("logistic.multi_org.id");
    public static final String MULTI_ORG_DELIVERY_DC1_NAME = getProperty("logistic.multi_org.delivery_dc1.name");
    public static final String MULTI_ORG_DELIVERY_DC1_ID = getProperty("logistic.multi_org.delivery_dc1.id");
    public static final String MULTI_ORG_DELIVERY_DC2_NAME = getProperty("logistic.multi_org.delivery_dc2.name");
    public static final String MULTI_ORG_DELIVERY_DC2_ID = getProperty("logistic.multi_org.delivery_dc2.id");
    public static final String MULTI_ORG_PICKUP_DC1_NAME = getProperty("logistic.multi_org.pickup_dc1.name");
    public static final String MULTI_ORG_PICKUP_DC1_ID = getProperty("logistic.multi_org.pickup_dc1.id");
    public static final String MULTI_ORG_PICKUP_DC2_NAME = getProperty("logistic.multi_org.pickup_dc2.name");
    public static final String MULTI_ORG_PICKUP_DC2_ID = getProperty("logistic.multi_org.pickup_dc2.id");
    public static final String MULTI_ORG_DELIVERY1_NAME = getProperty("logistic.multi_org.delivery1.name");
    public static final String MULTI_ORG_DELIVERY1_WMID = getProperty("logistic.multi_org.delivery1.wmid");
    public static final String MULTI_ORG_DELIVERY2_NAME = getProperty("logistic.multi_org.delivery2.name");
    public static final String MULTI_ORG_DELIVERY2_WMID = getProperty("logistic.multi_org.delivery2.wmid");
    public static final String MULTI_ORG_PICKUP1_NAME = getProperty("logistic.multi_org.pickup1.name");
    public static final String MULTI_ORG_PICKUP1_WMID = getProperty("logistic.multi_org.pickup1.wmid");
    public static final String MULTI_ORG_PICKUP2_NAME = getProperty("logistic.multi_org.pickup2.name");
    public static final String MULTI_ORG_PICKUP2_WMID = getProperty("logistic.multi_org.pickup2.wmid");
    public static final String MULTI_ORG_OWNER_NAME = getProperty("logistic.multi_org.owner_name");
    public static final String MULTI_ORG_OWNER_USER_ID = getProperty("logistic.multi_org.owner_user_id");
    public static final String MULTI_ORG_OWNER_CARD_ID = getProperty("logistic.multi_org.owner_card_id");
    public static final String MULTI_ORG_ADMIN_NAME = getProperty("logistic.multi_org.admin_name");
    public static final String MULTI_ORG_ADMIN_USER_ID = getProperty("logistic.multi_org.admin_user_id");
    public static final String MULTI_ORG_ADMIN_CARD_ID = getProperty("logistic.multi_org.admin_card_id");
    public static final String MULTI_ORG_DISPATCHER_NAME = getProperty("logistic.multi_org.dispatcher_name");
    public static final String MULTI_ORG_DISPATCHER_USER_ID = getProperty("logistic.multi_org.dispatcher_user_id");
    public static final String MULTI_ORG_DISPATCHER_CARD_ID = getProperty("logistic.multi_org.dispatcher_card_id");
    public static final String MULTI_ORG_DRIVER1_NAME = getProperty("logistic.multi_org.driver1_name");
    public static final String MULTI_ORG_DRIVER1_USER_ID = getProperty("logistic.multi_org.driver1_user_id");
    public static final String MULTI_ORG_DRIVER1_CARD_ID = getProperty("logistic.multi_org.driver1_card_id");
    public static final String MULTI_ORG_DRIVER2_NAME = getProperty("logistic.multi_org.driver2_name");
    public static final String MULTI_ORG_DRIVER2_USER_ID = getProperty("logistic.multi_org.driver2_user_id");
    public static final String MULTI_ORG_DRIVER2_CARD_ID = getProperty("logistic.multi_org.driver2_card_id");
    public static final String MULTI_ORG_DRIVER3_NAME = getProperty("logistic.multi_org.driver3_name");
    public static final String MULTI_ORG_DRIVER3_USER_ID = getProperty("logistic.multi_org.driver3_user_id");
    public static final String MULTI_ORG_DRIVER3_CARD_ID = getProperty("logistic.multi_org.driver3_card_id");
    public static final String MULTI_ORG_DRIVER4_NAME = getProperty("logistic.multi_org.driver4_name");
    public static final String MULTI_ORG_DRIVER4_USER_ID = getProperty("logistic.multi_org.driver4_user_id");
    public static final String MULTI_ORG_DRIVER4_CARD_ID = getProperty("logistic.multi_org.driver4_card_id");

    // Logistic org multi pickup DCs
    public static final String ORG_MULTI_PICKUP_NAME = getProperty("logistic.org_multi_pickup.name");
    public static final String ORG_MULTI_PICKUP_ID = getProperty("logistic.org_multi_pickup.id");
    public static final String ORG_MULTI_PICKUP_OWNER_NAME = getProperty("logistic.org_multi_pickup.owner_name");
    public static final String ORG_MULTI_PICKUP_OWNER_USER_ID = getProperty("logistic.org_multi_pickup.owner_user_id");
    public static final String ORG_MULTI_PICKUP_OWNER_CARD_ID = getProperty("logistic.org_multi_pickup.owner_card_id");
    public static final String ORG_MULTI_PICKUP_DC_1_NAME = getProperty("logistic.org_multi_pickup.dc1_name");
    public static final String ORG_MULTI_PICKUP_DC_1_ID = getProperty("logistic.org_multi_pickup.dc1_id");
    public static final String ORG_MULTI_PICKUP_LISTING_1_NAME = getProperty("logistic.org_multi_pickup.listing1_name");
    public static final String ORG_MULTI_PICKUP_LISTING_1_WMID = getProperty("logistic.org_multi_pickup.listing1_wmid");
    public static final String ORG_MULTI_PICKUP_DC_2_NAME = getProperty("logistic.org_multi_pickup.dc2_name");
    public static final String ORG_MULTI_PICKUP_DC_2_ID = getProperty("logistic.org_multi_pickup.dc2_id");
    public static final String ORG_MULTI_PICKUP_LISTING_2_NAME = getProperty("logistic.org_multi_pickup.listing2_name");
    public static final String ORG_MULTI_PICKUP_LISTING_2_WMID = getProperty("logistic.org_multi_pickup.listing2_wmid");
    public static final String ORG_MULTI_PICKUP_DC_3_NAME = getProperty("logistic.org_multi_pickup.dc3_name");
    public static final String ORG_MULTI_PICKUP_DC_3_ID = getProperty("logistic.org_multi_pickup.dc3_id");
    public static final String ORG_MULTI_PICKUP_LISTING_3_NAME = getProperty("logistic.org_multi_pickup.listing3_name");
    public static final String ORG_MULTI_PICKUP_LISTING_3_WMID = getProperty("logistic.org_multi_pickup.listing3_wmid");
    public static final String ORG_MULTI_PICKUP_DC_4_NAME = getProperty("logistic.org_multi_pickup.dc4_name");
    public static final String ORG_MULTI_PICKUP_DC_4_ID = getProperty("logistic.org_multi_pickup.dc4_id");
    public static final String ORG_MULTI_PICKUP_LISTING_4_NAME = getProperty("logistic.org_multi_pickup.listing4_name");
    public static final String ORG_MULTI_PICKUP_LISTING_4_WMID = getProperty("logistic.org_multi_pickup.listing4_wmid");
    public static final String ORG_MULTI_PICKUP_DC_5_NAME = getProperty("logistic.org_multi_pickup.dc5_name");
    public static final String ORG_MULTI_PICKUP_DC_5_ID = getProperty("logistic.org_multi_pickup.dc5_id");
    public static final String ORG_MULTI_PICKUP_LISTING_5_NAME = getProperty("logistic.org_multi_pickup.listing5_name");
    public static final String ORG_MULTI_PICKUP_LISTING_5_WMID = getProperty("logistic.org_multi_pickup.listing5_wmid");
    public static final String ORG_MULTI_PICKUP_DC_6_NAME = getProperty("logistic.org_multi_pickup.dc6_name");
    public static final String ORG_MULTI_PICKUP_DC_6_ID = getProperty("logistic.org_multi_pickup.dc6_id");
    public static final String ORG_MULTI_PICKUP_LISTING_6_NAME = getProperty("logistic.org_multi_pickup.listing6_name");
    public static final String ORG_MULTI_PICKUP_LISTING_6_WMID = getProperty("logistic.org_multi_pickup.listing6_wmid");
    public static final String ORG_MULTI_PICKUP_DC_7_NAME = getProperty("logistic.org_multi_pickup.dc7_name");
    public static final String ORG_MULTI_PICKUP_DC_7_ID = getProperty("logistic.org_multi_pickup.dc7_id");
    public static final String ORG_MULTI_PICKUP_LISTING_7_NAME = getProperty("logistic.org_multi_pickup.listing7_name");
    public static final String ORG_MULTI_PICKUP_LISTING_7_WMID = getProperty("logistic.org_multi_pickup.listing7_wmid");
    public static final String ORG_MULTI_PICKUP_DC_8_NAME = getProperty("logistic.org_multi_pickup.dc8_name");
    public static final String ORG_MULTI_PICKUP_DC_8_ID = getProperty("logistic.org_multi_pickup.dc8_id");
    public static final String ORG_MULTI_PICKUP_LISTING_8_NAME = getProperty("logistic.org_multi_pickup.listing8_name");
    public static final String ORG_MULTI_PICKUP_LISTING_8_WMID = getProperty("logistic.org_multi_pickup.listing8_wmid");
    public static final String ORG_MULTI_PICKUP_DC_9_NAME = getProperty("logistic.org_multi_pickup.dc9_name");
    public static final String ORG_MULTI_PICKUP_DC_9_ID = getProperty("logistic.org_multi_pickup.dc9_id");
    public static final String ORG_MULTI_PICKUP_LISTING_9_NAME = getProperty("logistic.org_multi_pickup.listing9_name");
    public static final String ORG_MULTI_PICKUP_LISTING_9_WMID = getProperty("logistic.org_multi_pickup.listing9_wmid");
    public static final String ORG_MULTI_PICKUP_DC_10_NAME = getProperty("logistic.org_multi_pickup.dc10_name");
    public static final String ORG_MULTI_PICKUP_DC_10_ID = getProperty("logistic.org_multi_pickup.dc10_id");
    public static final String ORG_MULTI_PICKUP_LISTING_10_NAME = getProperty("logistic.org_multi_pickup.listing10_name");
    public static final String ORG_MULTI_PICKUP_LISTING_10_WMID = getProperty("logistic.org_multi_pickup.listing10_wmid");
    public static final String ORG_MULTI_PICKUP_DISPATCHER_NAME = getProperty("logistic.org_multi_pickup.dispatcher_name");
    public static final String ORG_MULTI_PICKUP_DISPATCHER_USER_ID = getProperty("logistic.org_multi_pickup.dispatcher_user_id");
    public static final String ORG_MULTI_PICKUP_DISPATCHER_CARD_ID = getProperty("logistic.org_multi_pickup.dispatcher_card_id");
    public static final String ORG_MULTI_PICKUP_ADMIN_NAME = getProperty("logistic.org_multi_pickup.admin_name");
    public static final String ORG_MULTI_PICKUP_ADMIN_USER_ID = getProperty("logistic.org_multi_pickup.admin_user_id");
    public static final String ORG_MULTI_PICKUP_ADMIN_CARD_ID = getProperty("logistic.org_multi_pickup.admin_card_id");

    //  Logistics organization with one pickup DC that has a 30k+ orders data
    public static final String ORG_WITH_SINGLE_PICKUP_MANY_ORDERS_NAME = getProperty("logistic.org.with_single_pickup.many_orders.name");
    public static final String ORG_WITH_SINGLE_PICKUP_MANY_ORDERS_ID = getProperty("logistic.org.with_single_pickup.many_orders.id");
    public static final String ORG_DC_WITH_SINGLE_PICKUP_MANY_ORDERS_NAME = getProperty("logistic.org_dc.with_single_pickup.many_orders.name");
    public static final String ORG_DC_WITH_SINGLE_PICKUP_MANY_ORDERS_ID = getProperty("logistic.org_dc.with_single_pickup.many_orders.id");
    public static final String ORG_SINGLE_PICKUP_MANY_ORDERS_NAME = getProperty("logistic.org.single_pickup.many_orders.name");
    public static final String ORG_SINGLE_PICKUP_MANY_ORDERS_WMID = getProperty("logistic.org.single_pickup.many_orders.wmid");
    public static final String ORG_WITH_SINGLE_PICKUP_MANY_ORDERS_OWNER_NAME = getProperty("logistic.org.with_single_pickup.many_orders.owner_name");
    public static final String ORG_WITH_SINGLE_PICKUP_MANY_ORDERS_OWNER_USER_ID = getProperty("logistic.org.with_single_pickup.many_orders.owner_user_id");
    public static final String ORG_WITH_SINGLE_PICKUP_MANY_ORDERS_OWNER_CARD_ID = getProperty("logistic.org.with_single_pickup.many_orders.owner_card_id");
    public static final String ORG_WITH_SINGLE_PICKUP_MANY_ORDERS_DISPATCHER_NAME = getProperty("logistic.org.with_single_pickup.many_orders.dispatcher_name");
    public static final String ORG_WITH_SINGLE_PICKUP_MANY_ORDERS_DISPATCHER_USER_ID = getProperty("logistic.org.with_single_pickup.many_orders.dispatcher_user_id");
    public static final String ORG_WITH_SINGLE_PICKUP_MANY_ORDERS_DISPATCHER_CARD_ID = getProperty("logistic.org.with_single_pickup.many_orders.dispatcher_card_id");
    public static final String ORG_WITH_SINGLE_PICKUP_MANY_ORDERS_ADMIN_NAME = getProperty("logistic.org.with_single_pickup.many_orders.admin_name");
    public static final String ORG_WITH_SINGLE_PICKUP_MANY_ORDERS_ADMIN_USER_ID = getProperty("logistic.org.with_single_pickup.many_orders.admin_user_id");
    public static final String ORG_WITH_SINGLE_PICKUP_MANY_ORDERS_ADMIN_CARD_ID = getProperty("logistic.org.with_single_pickup.many_orders.admin_card_id");

    // Cannveya constants
    //Credentials
    public static final String CANNVEYA_ADMIN_NAME = getProperty("cannveya.admin_name");
    public static final String CANNVEYA_DRIVER_NAME = getProperty("cannveya.driver_name");
    public static final String CANNVEYA_SECOND_DRIVER_NAME = getProperty("cannveya.second_driver_name");
    public static final String CANNVEYA_PASSWORD = getEncryptedProperty("cannveya.admin_password");
    public static final String CANNVEYA_DRIVER_PASSWORD = getEncryptedProperty("cannveya.driver_password");
    public static final String CANNVEYA_DISPATCHER_NAME = getProperty("cannveya.dispatcher_name");
    public static final String CANNVEYA_DISPATCHER_PASSWORD = getEncryptedProperty("cannveya.dispatcher_password");
    // URLs
    public static final String CANNVEYA_PAGE = getProperty("cannveya.page");
    public static final String CANNVEYA_LOGIN_PAGE = CANNVEYA_PAGE + "manage/login";
    public static final String CANNVEYA_DELIVERIES_PAGE = CANNVEYA_PAGE + "manage#/deliveries";
    public static final String CANNVEYA_JOBS_PAGE = CANNVEYA_PAGE + "manage/#/jobs";
    public static final String CANNVEYA_SEARCH_JOBS_PAGE = CANNVEYA_JOBS_PAGE + "/search";
    public static final String CANNVEYA_EMPLOYEES_PAGE = CANNVEYA_PAGE + "manage/#/employees";
    public static final String CANNVEYA_EMPLOYEES_NOTIFICATIONS_PAGE = CANNVEYA_PAGE + "manage/#/employees/notifications";
    public static final String CANNVEYA_ACCOUNTING_PAGE = CANNVEYA_PAGE + "manage/#/employees/accounting";
    public static final String CANNVEYA_VEHICLES_PAGE = CANNVEYA_PAGE + "manage/#/vehicles";
    public static final String CANNVEYA_USERS_PAGE = CANNVEYA_PAGE + "manage/#/users";
    public static final String CANNVEYA_ACCOUNTS_PAGE = CANNVEYA_PAGE + "manage/#/accounts";
    public static final String CANNVEYA_INSIGHT_PAGE = CANNVEYA_PAGE + "manage/#/insight";
    public static final String CANNVEYA_REFERRALS_PAGE = CANNVEYA_PAGE + "manage/#/referrals";
    public static final String CANNVEYA_SETTINGS_PAGE = CANNVEYA_PAGE + "manage/#/settings";
    // API
    public static final String CANNVEYA_API_AUTH = CANNVEYA_PAGE + "api/auth";
    public static final String CANNVEYA_API_COMPANY = CANNVEYA_PAGE + "api/company";
    public static final String CANNVEYA_API_JOB_SEARCH = CANNVEYA_PAGE + "api/job/search";
    public static final String CANNVEYA_API_JOB = CANNVEYA_PAGE + "api/job";
    public static final String CANNVEYA_API_EMPLOYEE = CANNVEYA_PAGE + "api/employee";
    public static final String CANNVEYA_API_EMPLOYEE_SEARCH = CANNVEYA_PAGE + "api/employee/search";
    public static final String CANNVEYA_API_EMPLOYEE_UPDATELOCATION = CANNVEYA_PAGE + "api/employee/updatelocation";
    public static final String CANNVEYA_API_JOB_GETACTIVE = CANNVEYA_PAGE + "api/job/getActive";
    public static final String CANNVEYA_API_VEHICLE = CANNVEYA_PAGE + "api/vehicle";
    public static final String CANNVEYA_API_BILLING_PLANS = CANNVEYA_PAGE + "api/company/billing/plans";
    public static final String CANNVEYA_API_AUDIT = CANNVEYA_PAGE + "api/audit";
    public static final String CANNVEYA_DRIVER_RATING = CANNVEYA_PAGE + "api/rating/driver";
    public static final String CANNVEYA_ORGANIZATION_COMPANIES = CANNVEYA_PAGE + "api/organization/company/search";
    public static final String CANNVEYA_SMS_SEARCH = CANNVEYA_PAGE + "api/sms/search";
    public static final String CANNVEYA_MESSAGES = CANNVEYA_PAGE + "api/message";
    public static final String CANNVEYA_DISPATCH_EULA = CANNVEYA_PAGE + "api/application/eula";

    public static final String DISPATCH_PAYMENT_STATUSES = CANNVEYA_PAGE + "api/payment/statuses";

    // Logistics Retail organization for E2E integration testing
    public static final String E2E_INTEGRATION_RETAIL_ORG_NAME = getProperty("e2e_integration.retail.org_name");
    public static final String E2E_INTEGRATION_RETAIL_ORG_ID = getProperty("e2e_integration.retail.org_id");
    public static final String E2E_INTEGRATION_RETAIL_DC_NAME = getProperty("e2e_integration.retail.dc_name");
    public static final String E2E_INTEGRATION_RETAIL_DC_ID = getProperty("e2e_integration.retail.dc_id");
    public static final String E2E_INTEGRATION_RETAIL_PICKUP_NAME = getProperty("e2e_integration.retail.pickup_name");
    public static final String E2E_INTEGRATION_RETAIL_PICKUP_WMID = getProperty("e2e_integration.retail.pickup_wmid");
    public static final String E2E_INTEGRATION_RETAIL_PICKUP_OWNER_NAME = getProperty("e2e_integration.retail.pickup_owner_name");
    public static final String E2E_INTEGRATION_RETAIL_PICKUP_OWNER_USER_ID = getProperty("e2e_integration.retail.pickup_owner_user_id");
    public static final String E2E_INTEGRATION_RETAIL_ORG_ADMIN_NAME = getProperty("e2e_integration.retail.org_admin_name");
    public static final String E2E_INTEGRATION_RETAIL_ORG_ADMIN_PASSWORD = DEFAULT_PASSWORD;
    public static final String E2E_INTEGRATION_RETAIL_ORG_ADMIN_USER_ID = getProperty("e2e_integration.retail.org_admin_user_id");
    public static final String E2E_INTEGRATION_RETAIL_ORG_ADMIN_CARD_ID = getProperty("e2e_integration.retail.org_admin_card_id");
    public static final String WMR_PERFORMANCE_TESTING_ORG = getProperty("wmr.performance.testing.org");

    // Logistics Blaze organization for E2E integration testing
    public static final String E2E_INTEGRATION_BLAZE_ORG_NAME = getProperty("e2e_integration.blaze.org_name");
    public static final String E2E_INTEGRATION_BLAZE_ORG_ID = getProperty("e2e_integration.blaze.org_id");
    public static final String E2E_INTEGRATION_BLAZE_DC_NAME = getProperty("e2e_integration.blaze.dc_name");
    public static final String E2E_INTEGRATION_BLAZE_DC_ID = getProperty("e2e_integration.blaze.dc_id");
    public static final String E2E_INTEGRATION_BLAZE_PICKUP1_NAME = getProperty("e2e_integration.blaze.pickup1_name");
    public static final String E2E_INTEGRATION_BLAZE_PICKUP1_WMID = getProperty("e2e_integration.blaze.pickup1_wmid");
    public static final String E2E_INTEGRATION_BLAZE_PICKUP2_NAME = getProperty("e2e_integration.blaze.pickup2_name");
    public static final String E2E_INTEGRATION_BLAZE_PICKUP2_WMID = getProperty("e2e_integration.blaze.pickup2_wmid");
    public static final String E2E_INTEGRATION_BLAZE_PICKUP_OWNER_NAME = getProperty("e2e_integration.blaze.pickup_owner_name");
    public static final String E2E_INTEGRATION_BLAZE_PICKUP_OWNER_USER_ID = getProperty("e2e_integration.blaze.pickup_owner_user_id");
    public static final String E2E_INTEGRATION_BLAZE_ORG_ADMIN_NAME = getProperty("e2e_integration.blaze.org_admin_name");
    public static final String E2E_INTEGRATION_BLAZE_ORG_ADMIN_PASSWORD = DEFAULT_PASSWORD;
    public static final String E2E_INTEGRATION_BLAZE_ORG_ADMIN_USER_ID = getProperty("e2e_integration.blaze.org_admin_user_id");
    public static final String E2E_INTEGRATION_BLAZE_ORG_ADMIN_CARD_ID = getProperty("e2e_integration.blaze.org_admin_card_id");

    // Logistics Flowhub organization for E2E integration testing
    public static final String E2E_INTEGRATION_FLOWHUB_ORG_NAME = getProperty("e2e_integration.flowhub.org_name");
    public static final String E2E_INTEGRATION_FLOWHUB_ORG_ID = getProperty("e2e_integration.flowhub.org_id");
    public static final String E2E_INTEGRATION_FLOWHUB_DC_NAME = getProperty("e2e_integration.flowhub.dc_name");
    public static final String E2E_INTEGRATION_FLOWHUB_DC_ID = getProperty("e2e_integration.flowhub.dc_id");
    public static final String E2E_INTEGRATION_FLOWHUB_PICKUP1_NAME = getProperty("e2e_integration.flowhub.pickup1_name");
    public static final String E2E_INTEGRATION_FLOWHUB_PICKUP1_WMID = getProperty("e2e_integration.flowhub.pickup1_wmid");
    public static final String E2E_INTEGRATION_FLOWHUB_PICKUP_OWNER_NAME = getProperty("e2e_integration.flowhub.pickup_owner_name");
    public static final String E2E_INTEGRATION_FLOWHUB_PICKUP_OWNER_USER_ID = getProperty("e2e_integration.flowhub.pickup_owner_user_id");
    public static final String E2E_INTEGRATION_FLOWHUB_ORG_ADMIN_NAME = getProperty("e2e_integration.flowhub.org_admin_name");
    public static final String E2E_INTEGRATION_FLOWHUB_ORG_ADMIN_PASSWORD = DEFAULT_PASSWORD;
    public static final String E2E_INTEGRATION_FLOWHUB_ORG_ADMIN_USER_ID = getProperty("e2e_integration.flowhub.org_admin_user_id");
    public static final String E2E_INTEGRATION_FLOWHUB_ORG_ADMIN_CARD_ID = getProperty("e2e_integration.flowhub.org_admin_card_id");

    // WM Store users
    public static final String WM_STORE_BASE_USER_NAME = getProperty("wm_store.base_user.name");
    public static final String WM_STORE_STORE_OWNER_NAME = getProperty("wm_store.store_owner.name");
    public static final String WM_STORE_BRAND_OWNER_NAME = getProperty("wm_store.brand_owner.name");

    //MailTrap
    public static final String MAILTRAP_PAGE = getProperty("mailtrap.url.prefix");
    public static final String MAILTRAP_LOGIN = getProperty("mailtrap.login");
    public static final String MAILTRAP_PASSWORD = getEncryptedProperty("mailtrap.password");

    // testrail
    public static final String TESTRAIL_USER_AUTH = getEncryptedProperty("testrail.user.auth");

    //BeamTeam
    public static final String API_BEAM_BASE_URL = getProperty("api.beam.email.url.prefix");
    public static final String API_BEAM_EMAIL_V1_PREFIX = getProperty("api.beam.email.v1.prefix");
    public static final String API_POTIONEERS_CATALOG_URL = getProperty("api.beam.catalog.v1.prefix");
    public static final String ONE_TIME_TOKEN_PAGE = getProperty("one.time.token.page");
    public static final String FEATURE_FLAG_ADMIN = getProperty("feature.flag.admin.page");

    public static final String API_BEAM_FEATURE_FLAG = getProperty("api.beam.feature.flag.page");
    public static final String POLICIES = getProperty("api.beam.policies.v1.prefix");
    public static final String API_AIM_CORE_LEGACY = getProperty("api.core.legacy.prefix");
    public static final String API_AIM_INTERNAL_LEGACY = getProperty("api.iam.g.prefix");
    public static final String FEATURE_FLAG_INTERNAL_API = getProperty("feature.flag.internal.api");
    public static final String API_INTEGRATORS_SERVICE = getProperty("api.beam.integrators.service.prefix");
    public static final String INTERNAL_API_INTEGRATORS_SERVICE = getProperty("api.internal.integrators.service.prefix");

    //Facebook
    public static final String FACEBOOK_LOGIN = getProperty("facebook.login");
    public static final String FACEBOOK_PASSWORD = getEncryptedProperty("facebook.password");

    /*
    Do not create user with this account!!!
    */
    public static final String FACEBOOK_NOT_LINKED_LOGIN = getProperty("facebook.not_linked_login");
    public static final String FACEBOOK_NOT_LINKED_PASSWORD = getEncryptedProperty("facebook.not_linked_password");

    //Revive Adserver
    public static final String ADSERVER_ADMIN_PAGE = getProperty("adserver.admin.page");
    public static final String ADSERVER_LOGIN = getProperty("adserver.login");
    public static final String ADSERVER_PASSWORD = getEncryptedProperty("adserver.password");

    //Google
    public static final String GOOGLE_WM_USERNAME = getProperty("google.wm_username");
    public static final String GOOGLE_LOGIN = getProperty("google.login");
    public static final String GOOGLE_PASSWORD = getEncryptedProperty("google.password");

    /*
    Do not create user with this account!!!
     */
    public static final String GOOGLE_NOT_LINKED_LOGIN = getProperty("google.not_linked_login");
    public static final String GOOGLE_NOT_LINKED_PASSWORD = getEncryptedProperty("google.not_linked_password");

    public static final String BING_MAPS_KEY = getProperty("bing.maps.key");

    //Phone users
    public static final String MOBILE_PHONE_USERS_LOGIN = getProperty("mobile.phone.users.login");
    public static final String MOBILE_PHONE_USERS_EMAIL_POSTFIX = getProperty("mobile.phone.users.email_postfix");
    public static final String DESKTOP_PHONE_USERS_LOGIN = getProperty("desktop.phone.users.login");
    public static final String DESKTOP_PHONE_USERS_EMAIL_POSTFIX = getProperty("desktop.phone.users.email_postfix");

    //OOS users
    public static final String OOS_API_USERS_LOGIN = getProperty("oos.api.users.login");
    public static final String OOS_API_USERS_EMAIL_POSTFIX = getProperty("oos.api.users.email_postfix");

    //Twilio
    public static final String TWILIO_URL = getEncryptedProperty("twilio.url");
    public static final String TWILIO_NUMBER = getProperty("twilio.number");
    public static final String TWILIO_LOGIN = getEncryptedProperty("twilio.username");
    public static final String TWILIO_PASSWORD = getEncryptedProperty("twilio.password");
    public static final String TWILIO_TOKEN = getEncryptedProperty("twilio.token");


    //Credentials WM Exchange
    public static final String WMX_SUPER_USER_EMAIL = getProperty("wmx.super.user.email");
    public static final String WMX_SUPER_USER_LOGIN = getProperty("wmx.super.user.login");
    public static final String WMX_SUPER_USER_PASSWORD = getEncryptedProperty("wmx.super.user.password");
    public static final String MULTI_USER_EMAIL = getProperty("multi.user.email");
    public static final String MULTI_USER_LOGIN = getProperty("multi.user.login");
    public static final String MULTI_USER_PASSWORD = getEncryptedProperty("multi.user.password");
    public static final String SELLER_USER_EMAIL = getProperty("seller.user.email");
    public static final String SELLER_USER_LOGIN = getProperty("seller.user.login");
    public static final String SELLER_USER_PASSWORD = getEncryptedProperty("seller.user.password");
    public static final String NO_ORDERS_SELLER_USER_EMAIL = getProperty("noordersseller.user.email");
    public static final String NO_ORDERS_SELLER_USER_LOGIN = getProperty("noordersseller.user.login");
    public static final String NO_ORDERS_SELLER_USER_PASSWORD = getEncryptedProperty("noordersseller.user.password");
    public static final String BUYER_USER_EMAIL = getProperty("buyer.user.email");
    public static final String BUYER_USER_LOGIN = getProperty("buyer.user.login");
    public static final String BUYER_USER_PASSWORD = getEncryptedProperty("buyer.user.password");
    public static final String NO_ORDERS_BUYER_USER_EMAIL = getProperty("noordersbuyer.user.email");
    public static final String NO_ORDERS_BUYER_USER_LOGIN = getProperty("noordersbuyer.user.login");
    public static final String NO_ORDERS_BUYER_USER_PASSWORD = getEncryptedProperty("noordersbuyer.user.password");
    public static final String SALES_EMAIL = getProperty("sales.email");

    // WMX data constants
    public static final String BUYER_LOCATION_ID = getProperty("wmx.buyer.location.id");
    public static final String BUYER_LOCATION_NAME = getProperty("wmx.buyer.location.name");

    // Expired token
    public static final String EXPIRED_TOKEN = getEncryptedProperty("expired.token");

    public static final int DATA_ITEMS_COUNT = System.getProperty("dataItemsCount") == null
            ? 1
            : Integer.parseInt(System.getProperty("dataItemsCount"));


    /**
     * Get property value and decrypt it
     *
     * @param property property name
     * @return
     */
    protected static String getEncryptedProperty(String property) {
        String propertyValue = getProperty(property);
        if (mpropkey == null || mpropkey.isEmpty()) {
            return propertyValue;
        }
        return new EncryptSettings(mpropkey.getBytes()).decryptString(propertyValue);
    }

    /**
     * Get property and split it to list
     *
     * @param property
     * @param delimiter
     * @return
     */
    protected static List<String> getListProperty(String property, String delimiter) {
        return Arrays
                .stream(getProperty(property).split(delimiter))
                .collect(Collectors.toList());
    }

    /**
     * Get property and split it to list by default delimiter
     *
     * @param property
     * @return
     */
    protected static List<String> getListProperty(String property) {
        return getListProperty(property, "\\|");
    }

    /**
     * Get property, split it to list and add postfix for every item
     *
     * @param property
     * @param delimiter
     * @param addPostfix
     * @return
     */
    protected static List<String> getListPropertyWithPostfix(String property, String delimiter, String addPostfix) {
        return Arrays
                .stream(getProperty(property).split(delimiter))
                .map((s) -> s + addPostfix)
                .collect(Collectors.toList());
    }

    /**
     * Get property, split it to list by default delimiter and add postfix for every item
     *
     * @param property
     * @param addPostfix
     * @return
     */
    protected static List<String> getListPropertyWithPostfix(String property, String addPostfix) {
        return getListPropertyWithPostfix(property, "\\|", addPostfix);
    }

    @Test
    public static void encryptString() throws Exception {

        String stringToEncrypt = "PUT HERE A STRING TO ENCRYPT. Don't forget to remove your value from here.";

        if (mpropkey == null || mpropkey.isEmpty())
            throw new Exception("Specify decryption key using '-Dmpropkey'");
        Log.debug("String to encrypt: " + stringToEncrypt);
        String encryptedString = new EncryptSettings(mpropkey.getBytes()).encryptString(stringToEncrypt);
        Log.debug("Encrypted string: " + encryptedString);
    }

    @Test
    public static void decryptProperty() {
        Log.debug("Decrypted property: " + SUPER_USER_PASSWORD);
    }
}
