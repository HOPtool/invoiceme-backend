/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoptool.resources;

/**
 *
 * @author root
 */
public class ErrorCodes {
    
    public static final int SUCCESSFUL = 200;
    public static final int CREATED = 201;
    public static final int ACCEPTED = 202;
    public static final int INVALID_USER_JWT = 710;
    public static final int INVALID_SEARCH_PARAM = 711;
    public static final int INVALID_PAGE_ID_OR_FETCH_SIZE = 712;
    public static final int NO_RECORD_FOUND = 713;
    public static final int OTP_USED = 714;
    public static final int OTP_INVALID = 715;
    public static final int OTP_EXPIRED = 716;
    public static final int SUCCESSFUL_PENDING_OTP_VERIFICATION = 717;
    public static final int INVALID_VERIFY_PASSWORD = 718;
    public static final int CLIENT_EXISTS = 719;
    public static final int INVALID_APP_ID = 720;
    public static final int INVALID_CHANNEL = 795;
    public static final int INVALID_TENOR = 796;
    public static final int INVALID_START_DATE = 797;
    public static final int INVALID_END_DATE = 798;
    public static final int DATE_DISPARITY = 799;
    public static final int CONFIG_ERROR = 800;
    public static final int IO_EXCEPTION = 801;
    public static final int INVALID_SCHEME = 811;
    public static final int INVALID_SERVICE = 812;
    public static final int INVALID_WALLET = 813;
    public static final int INVALID_ACCOUNT = 814;
    public static final int INVALID_FUNCTION = 815;
    public static final int INSUFFICIENT_BALANCE = 816;
    public static final int TRANSACTION_NOT_PERMITED_TERMINAL = 817;
    public static final int TRANSACTION_NOT_PERMITED_WALLET = 818;
    public static final int INVALID_CURRENCY = 819;
    public static final int TRANS_SRC_AND_DEST_SAME = 820;
    public static final int TRANSACTION_NOT_FOUND = 821;
    public static final int EXPIRED_JWT = 823;
    public static final int INVALID_DEST_ACCOUNT = 824; // consider making this token expired
    public static final int INVALID_DOB = 825;
    public static final int INVALID_BVN = 826;
    public static final int INVALID_USER_OR_PASSWORD = 850;
    public static final int INVALID_USER_CODE = 851;
    public static final int INVALID_USER_CODELINK = 852;
    public static final int INVALID_PASSWORD = 853;
    public static final int INVALID_CONFIRM_PASSWORD = 854;
    public static final int INVALID_CLIENT = 901;
    public static final int DECRYPTION_ERROR = 902;
    public static final int INVALID_TERMINAL_ID = 903;
    public static final int FORMAT_ERROR = 904;
    public static final int DATABASE_ERROR = 905;
    public static final int SYSTEM_ERROR = 906; // consider making this token expired
    public static final int COMM_LINK = 907;
    public static final int ACCOUNT_EXISTS = 908;
    public static final int ISSUER_OR_SWITCH = 911;
    public static final int DUPLICATE_TRANSACTION = 913;
    public static final int INVALID_PHONE_NO = 914;
    public static final int INVALID_LASTNAME = 915;
    public static final int INVALID_FIRSTNAME = 916;
    public static final int INVALID_EMAIL = 917;
    public static final int INVALID_REQUESTID = 918;
    public static final int INVALID_AMOUNT = 919;
    public static final int MALFORMED_REQUEST = 920; // consider making this token expired
    public static final int INVALID_JSON_FORMAT = 921;
    public static final int INVALID_PARTNER_CLASS = 922;
    
    public static final int INVALID_DOMAIN = 923;
    public static final int INVALID_PARTNER_CODE = 924;
    public static final int INVALID_TRANSACTION = 925;
    public static final int SECURITY_VIOLATION = 926;
    public static final int INVALID_ROLE = 927;
    public static final int TRANSACTION_FORBIDDEN = 928;
    public static final int INVALID_USERNAME = 929;
    
    public static final int INVALID_SCHEMENAME = 930;
    public static final int INVALID_SCHEMENAME_SHORTCODE = 931;
    public static final int INVALID_SCHEME_RATE = 932;
    public static final int INVALID_EFFECTIVE_DATE = 933;
    public static final int INVALID_LOAN_LIQUIDATION_DATE = 934;
    public static final int INVALID_SCHEME_POSITION = 935;
    public static final int INVALID_SCHEME_POSITION_BANK = 936;
    public static final int ACTIVE_LOANS_LIMIT_EXCEEDED = 937;
    public static final int SECURITY_VIOLATION_BL = 938;
    public static final int LIMIT_EXCEEDED = 939;
    public static final int ERROR_VERIFYING_APPROVAL = 940;
    public static final int APPROVAL_ERROR = 941;
    public static final int JWT_SIGNATURE_EXCEPTION = 942;
    public static final int INVALID_LOAN_SETTING_PARAM = 943;
    public static final int INVALID_USERNAME_OR_PASSWORD = 944;
    public static final int INVALID_OFFER_ACTION = 945;
    public static final int OFFER_DECLINED_BY_LOANEE = 946;
    public static final int SUCCESSFULLY_ACCEPTED_PENDING_DISBURSEMENT = 947;
    public static final int INVALID_BUSINESS_ID = 948;
    public static final int INVALID_LOAN_ID = 949;
    public static final int LOANEE_COMMENT = 950;
    public static final int OFFER_EXPIRED = 951;
    public static final int INACTIVE_PREAPPROVAL = 952;
    public static final int INVALID_TOKEN = 953;
    public static final int INVALID_MENU_NAME = 954;
    public static final int INVALID_PARENT_MENU = 955;
    public static final int INVALID_URL = 956;
    public static final int INVALID_SUBMENU = 957;
    public static final int INVALID_STAGE = 958;
    public static final int  USER_ALREADY_EXIST = 959;
    public static final int  PASSWORD_CHANGE_REQUIRED = 960;
    public static final int  INVALID_DEFPASSWORD = 961;
    public static final int  INVALID_BUSINESS_NAME = 962;
    public static final int  INVALID_ADDRESS = 963;
    public static final int  INVALID_STATE = 964;
    
    public static final int  INVALID_PIN = 965;
    public static final int  INVALID_VERIFY_PIN = 966;
    //public static final int  INVALID_STATE = 965;
    
    
    public static String doErroDesc(int code) {
        //System.out.println(" ****doErroDesc code = " + code);
        String desc = "";
        switch(code)
        {
           
            case 200:
                desc = "SUCCESSFUL";
                break;
            case 201:
                desc = "CREATED";
                 break;
            case 202:
                desc = "ACCEPTED";
                 break;
            case 111:
                desc = "INVALID SCHEME";
                 break;
            case 112:
                desc = "INVALID SERVICE";
                 break;
            case 113:
                desc = "INVALID WALLET";
                 break;
            case 114:
                desc = "INVALID ACCOUNT";
                 break;
            case 115:
                desc = "INVALID FUNCTION";
                 break;
            case 116:
                desc = "INSUFFICIENT BALANCE";
                 break;
            case 117:
                desc = "TRANSACTION NOT PERMITED TERMINAL";
                 break;
            case 118:
                desc = "TRANSACTION NOT PERMITED WALLET";
                 break;
            case 119:
                desc = "INVALID CURRENCY";
                 break;
            case 120:
                desc = "TRANS SRC AND DEST SAME SERVICE";
                 break;
            case 121:
                desc = "TRANSACTION NOT FOUND";
                 break;
            case 123:
                desc = "EXPIRED JWT";
                 break;
            case 124:
                desc = "INVALID DEST ACCOUNT";
                 break;
            case 125:
                desc = "INVALID DOB";
                 break;
                 
            case INVALID_USER_JWT:
                  desc = "INVALID_USER_JWT";
                 break;
                 
            case INVALID_SEARCH_PARAM:
                  desc = "INVALID_SEARCH_PARAM";
                 break;
                 
             case INVALID_PAGE_ID_OR_FETCH_SIZE:
                  desc = "INVALID_PAGE_ID_OR_FETCH_SIZE";
                 break;
                 
             case NO_RECORD_FOUND:
                  desc = "NO_RECORD_FOUND";
                 break;
                 
                case OTP_USED:
            desc = "OTP_USED";
                 break;
                 
               case OTP_INVALID:
            desc = "OTP_INVALID";
                 break;
                 
              
                 
             case OTP_EXPIRED:
            desc = "OTP_EXPIRED";
                 break;
                 
            case SUCCESSFUL_PENDING_OTP_VERIFICATION:
            desc = "SUCCESSFUL_PENDING_OTP_VERIFICATION";
                 break;
            case INVALID_VERIFY_PASSWORD:
                desc = "INVALID_VERIFY_PASSWORD";
                break;
            case INVALID_CONFIRM_PASSWORD:
                desc = "INVALID_CONFIRM_PASSWORD";
                //System.out.println("------ desc = " +  desc);
                break;
//             case INVALID_VERIFY_PASSWORD:
//                desc = "INVALID_VERIFY_PASSWORD";
//                break;
            case CLIENT_EXISTS:
                desc = "CLIENT_EXISTS";
                break;
            case INVALID_APP_ID:
                desc = "INVALID_APP_ID";
                 break;
            case 901:
                desc = "INVALID CLIENT";
                 break;
            case 902:
                desc = "DECRYPTION ERROR";
                 break;
            case 903:
                desc = "INVALID TERMINAL ID";
                 break;
            case 904:
                desc = "FORMAT ERROR";
                 break;
            case 905:
                desc = "DATABASE ERROR";
                break;
            case 906:
                desc = "SYSTEM ERROR";
                 break;
            case ACCOUNT_EXISTS:
                desc = "ACCOUNT_EXISTS";
                break;
            case 913:
                desc = "DUPLICATE TRANSACTION";
                 break;
            case 914:
                desc = "INVALID PHONE NO";
                 break;
            case 915:
                desc = "INVALID LASTNAME";
                 break;
            case 916:
                desc = "INVALID FIRSTNAME";
                 break;
            case 917:
                desc = "INVALID_EMAIL";
                 break;
            case 918:
                desc = "INVALID REQUEST ID";
                 break;
            case 919:
                desc = "INVALID AMOUNT";
                 break;
            case 920:
                desc = "MALFORMED REQUEST";
                 break;
            case 921:
                desc = "INVALID JSON FORMAT";
                 break;
            case 922:
                desc = "INVALID PARTNER CLASS";
                 break;
             case 923:
                desc = "INVALID DOMAIN";  
                 break;
                 
                 
             case INVALID_BUSINESS_ID:
                desc = "INVALID_BUSINESS_ID";  
                 break;
            
             case INVALID_VERIFY_PIN:
                desc = "INVALID_VERIFY_PIN";  
                 break;
                 
            case INVALID_USERNAME_OR_PASSWORD:
                desc = "INVALID_USERNAME_OR_PASSWORD";  
                 break;
                 
            case JWT_SIGNATURE_EXCEPTION:
                desc = "JWT_SIGNATURE_EXCEPTION";  
                break;
            
                
                 
               
            default:
                desc = "UNKOWN ERROR "+code;
                 break;
             
              
        }
        
     return desc.replaceAll("_", " ").toLowerCase();
    }
    
}
