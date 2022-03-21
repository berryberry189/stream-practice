package com.grace.streampractice.chap7;

import com.grace.streampractice.chap7.model.User;

import java.util.Optional;

public class Chapter7Optional {
    public static void main(String[] args) {
        User user1 = new User()
                .setId(1001)
                .setName("Alice")
                .setVerified(true);
        User user2 = new User()
                .setId(1001)
                .setName("Alice")
                .setEmailAddress("aloce@email.com")
                .setVerified(true);

        //  user1의 email값이 없으므로 nullPointException
        //System.out.println(userEquals(user1, user2));

        String someEmail = "some@email.com";
        String nullEmail = null;

        Optional<String> maybeEmail = Optional.of(someEmail);
        Optional<String> maybeEmail2 = Optional.empty();
        Optional<String> maybeEmail3 = Optional.ofNullable(someEmail);
        Optional<String> maybeEmails = Optional.ofNullable(nullEmail);

        String email = maybeEmail.get();
        System.out.println(email);
        // error
        //System.out.println(maybeEmail2.get());

        if(maybeEmail2.isPresent()){
            System.out.println(maybeEmail2.get());
        }

        String defaultEmail = "default@email.com";
        String email3 = maybeEmail2.orElse(defaultEmail);
        //System.out.println(email3);
        // orElseGet supplier를 만들어줌
        String email4 = maybeEmail2.orElseGet(()->defaultEmail);
        // System.out.println(email4);
        String email5 = maybeEmail2.orElseThrow(()->new RuntimeException("email not present"));
        System.out.println(email5);

    }

    public static boolean userEquals(User u1, User u2){
        return u1.getId() == u2.getId()
                && u1.getName().equals(u2.getName())
                && u1.getEmailAddress().equals(u2.getEmailAddress())
                && u1.isVerified() == u2.isVerified();
    }

}
