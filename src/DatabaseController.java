/*
Parker Jones
11/09/17
Database Controller
 */

import java.util.*;

public class DatabaseController {

    ArrayList<Member> members = new ArrayList<Member>();
    ArrayList<Provider> providers = new ArrayList<Provider>();


    public void getServiceRecord() {

    }

    public void newMember(String firstName, String lastName, String streetAddress, String city, String state, String zipCode,String email, int phoneNumber, int userID) {
        members.add(new Member(firstName, lastName, streetAddress, city, state, zipCode, email, phoneNumber, userID));
    }

    public void newProvider(String firstName, String lastName, String streetAddress, String city, String state, String zipCode,String email, int phoneNumber, int userID) {
        providers.add(new Provider(firstName, lastName, streetAddress, city, state, zipCode, email, phoneNumber, userID));
    }


    public void deleteMember(int userID) {
        members.remove(getMember(userID));
    }

    public void suspendMember(int userID) {
        getMember(userID).suspend();
    }

    public void addServiceCode() {

    }



    public Member getMember(int userID) {
        for (Member member : members) {
            if (member.getUserID() == userID) {
                return member;
            }
        }
        System.out.println("NO USER BY THAT ID FOUND");

        return new Member("-1", "-1", "-1", "-1", "-1", "-1", "-1", -1, -1);
    }


    public Provider getProvider(int userID) {
        for (Provider provider : providers) {
            if (provider.getUserID() == userID) {
                return provider;
            }
        }
        System.out.println("NO USER BY THAT ID FOUND");

        return new Provider("-1", "-1", "-1", "-1", "-1", "-1", "-1", -1, -1);
    }



    public void searchServiceRecords(int userNum) {



    }

    public void searchServiceRecords(Date start, Date end) {


    }

}