/*
 * Parker Jones
 * 11/09/17
 * Database Controller
 */

import java.util.*;
import java.io.*;

public class DatabaseController {

    static ArrayList<Member> members = new ArrayList<Member>();
    static ArrayList<Provider> providers = new ArrayList<Provider>();
    static ArrayList<ServiceRecord> serviceRecords = new ArrayList<ServiceRecord>();
    static ArrayList<ServiceCode> serviceCodes = new ArrayList<ServiceCode>();




    //creates another member in the list
    public static void newMember(String firstName, String lastName, String streetAddress,
                          String city, String state, String zipCode,String email,
                          int phoneNumber) {

        int newUserID = 100000;

        if(members.size() > 0) {
            newUserID += members.size();
        }

        members.add(new Member(firstName, lastName, streetAddress, city, state, zipCode, email, phoneNumber, newUserID ));
    }


    //creates a new provider in the list
    public static void newProvider(String firstName, String lastName, String streetAddress,
                            String city, String state, String zipCode,String email,
                            int phoneNumber) {

        int newProviderID = 100000;

        if(providers.size() > 0) {
            newProviderID += providers.size();
        }

        providers.add(new Provider(firstName, lastName, streetAddress, city, state, zipCode, email, phoneNumber, newProviderID));
    }


    //deletes the member with the corresponding ID
    public static void deleteMember(int userID) {
        members.remove(getMember(userID));
    }


    //suspends the member with the corresponding ID
    public static void suspendMember(int userID) {
        getMember(userID).suspend();
    }


    //returns the Member object of the member with the corresponding ID
    public static Member getMember(int userID) {
        for (Member member : members) {
            if (member.getUserID() == userID) {
                return member;
            }
        }
        System.out.println("NO USER BY THAT ID FOUND");

        return new Member("-1", "-1", "-1", "-1", "-1", "-1", "-1", -1, -1);
    }


    //returns the Provider object of the provider with the correspondin ID
    public static Provider getProvider(int userID) {
        for (Provider provider : providers) {
            if (provider.getUserID() == userID) {
                return provider;
            }
        }
        System.out.println("NO USER BY THAT ID FOUND");

        return new Provider("-1", "-1", "-1", "-1", "-1", "-1", "-1", -1, -1);
    }


    //creates a new Service Code with the next available ID
    public static void addServiceCode(String name, double fee) {

        int newCode = 100000;

        if(serviceCodes.size() > 0) {
            newCode += serviceCodes.size();
        }

        serviceCodes.add(new ServiceCode(name, newCode, fee));

    }


    //search the service codes by name
    public static ServiceCode searchServiceCodes(String name) {

        ServiceCode tempCode = new ServiceCode("-1", -1, -1);

        for (ServiceCode serviceCode : serviceCodes) {
            if( serviceCode.serviceName.equals(name)) {
                tempCode = serviceCode;
            }
        }

        return tempCode;

    }


    //search the service codes by the code
    public static ServiceCode searchServiceCodes(int code) {

        ServiceCode tempCode = new ServiceCode("-1", -1, -1);

        for (ServiceCode serviceCode : serviceCodes) {
            if( serviceCode.serviceCode == code) {
                tempCode = serviceCode;
            }
        }

        return tempCode;

    }


    //search the service records by member
    public static ServiceRecord[] searchServiceRecords(Member member) {

        ArrayList<ServiceRecord> tempRecords = new ArrayList<>();

        for ( ServiceRecord record : serviceRecords ) {

            if( record.MemberNumber == member.getUserID() ) {
                tempRecords.add(record);
            }
        }

        ServiceRecord[] matchingRecords = tempRecords.toArray(new ServiceRecord[tempRecords.size()]);

        return matchingRecords;
    }


    //search the service records by provider
    public static ServiceRecord[] searchServiceRecords(Provider provider){

        ArrayList<ServiceRecord> tempRecords = new ArrayList<>();

        for ( ServiceRecord record : serviceRecords ) {

            if( record.ProviderNumber == provider.getUserID() ) {
                tempRecords.add(record);
            }
        }

        ServiceRecord[] matchingRecords = tempRecords.toArray(new ServiceRecord[tempRecords.size()]);

        return matchingRecords;

    }


    //search the service records by Time period TODO this is Incomplete
    public static void searchServiceRecords(Date start, Date end) {



    }


    public static void createMemberRecord(Member member) {

        MemberReport report = new MemberReport(member);

    }

}