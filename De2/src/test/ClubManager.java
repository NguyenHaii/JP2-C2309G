package test;

import entity.Member;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class ClubManager {
    public static void main(String[] args) throws IOException {
        List<Member> members = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 3; i++) {
            System.out.println("Enter details for member " + (i + 1) + ":");
            Member member = new Member();

            String memberID;
            do {
                System.out.print("Enter memberID (e.g., TMB12345): ");
                memberID = reader.readLine();
            } while (!isValidMemberID(memberID));
            member.setMemberID(memberID);

            System.out.print("Enter member name: ");
            member.setMemberName(reader.readLine());

            System.out.print("Enter address: ");
            member.setAddress(reader.readLine());

            members.add(member);
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("member_of_club.txt"))) {
            oos.writeObject(members);
        }

        List<Member> readMembers;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("member_of_club.txt"))) {
            readMembers = (List<Member>) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error reading members", e);
        }

        System.out.println("Members from file:");
        readMembers.forEach(System.out::println);
    }

    private static boolean isValidMemberID(String memberID) {
        String regex = "^[TVA](MB|MT|MN)\\d{5}$";
        return Pattern.matches(regex, memberID);
    }

}
