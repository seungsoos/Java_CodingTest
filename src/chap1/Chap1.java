package chap1;

import java.util.*;

public class Chap1 {


    /**
     * 전화번호부 나타내기
     */
    private static class PhoneBook{
        private final Set<Person> people;

        public PhoneBook() {
            this.people = new HashSet<>();
        }

        public void addPerson(Person person) {
            people.add(person);
        }

        public Person search(PhoneNumber number) {
            return people.stream()
                    .filter(person -> person.hasPhoneNumber(number))
                    .findFirst()
                    .orElse(null);
        }
        @Override
        public String toString() {
            return "PhoneBook{" +
                    "people=" + people +
                    '}';
        }

        public static void main(String[] args) {
            /*
            테스트
            Person person1 = new Person("테스트1");
            person1.addPhoneNumber(new PhoneNumber("010-1234-5678"));
            person1.addPhoneNumber(new PhoneNumber("010-2345-6789"));

            Person person2 = new Person("테스트2");
            person2.addPhoneNumber(new PhoneNumber("010-2345-7846"));

            Person person3 = new Person("테스트3");
            person2.addPhoneNumber(new PhoneNumber("010-2345-7846"));

            PhoneBook phoneBook = new PhoneBook();
            phoneBook.addPerson(person1);
            phoneBook.addPerson(person2);
            phoneBook.addPerson(person3);

            System.out.println("phoneBook = " + phoneBook);
            */
            /*
            List 이기때문에 중복이 허용됨.
            Person person1 = new Person("테스트1");
            person1.addPhoneNumber(new PhoneNumber("010-1234-5678"));
            PhoneBook phoneBook = new PhoneBook();
            phoneBook.addPerson(person1);
            phoneBook.addPerson(person1);
            System.out.println("phoneBook = " + phoneBook);
            */

           /*
           Set으로 수정하여 중복을 제거함.
            */
            Person person1 = new Person("테스트1");
            person1.addPhoneNumber(new PhoneNumber("010-1234-5678"));
            PhoneBook phoneBook = new PhoneBook();
            phoneBook.addPerson(person1);
            phoneBook.addPerson(person1);
            System.out.println("phoneBook = " + phoneBook);

        }
    }

    /**
     * 전화번호부의 사람 나타내기
     */
    private static class Person {
        public final String name;
        private final List<PhoneNumber> phoneNumbers;

        public Person(String name) {
            this.name = name;
            this.phoneNumbers = new ArrayList<>();
        }

        public void addPhoneNumber(PhoneNumber number) {
            phoneNumbers.add(number);
        }

        /**
         * String으로 표현했다면 어떤 형식이든 받을 수 있다.
         * 메서드로 사용시 전화번호형식까지 검사가 가능하다.
         */
//        public boolean addPhoneNumber(String number){
//            for (char c : number.toCharArray()) {
//                if (!Character.isDigit(c)) {
//                    return false;
//                }
//            }
//            phoneNumbers.add(number);
//            return true;
//        }
//    }
        public boolean hasPhoneNumber(PhoneNumber number) {
            return phoneNumbers.contains(number);
        }


        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", phoneNumbers=" + phoneNumbers +
                    '}';
        }
    }

    public static void main(String[] args) {
        /**
         * contains 는 equals 메서드를 사용하여 객체를 비교함.
         * 객체비교시 equals() 오버라이딩을 하여 동일성을 보장
         */
        Person person = new Person("홍길동");
        person.addPhoneNumber(new PhoneNumber("010-1234-5678"));
        System.out.println(person.hasPhoneNumber(new PhoneNumber("01012345678")));
    }




    /**
     * 전화번호 나타내기
     * 전화번호 입력시 형식 수정
     */
    private static class PhoneNumber{
        public final String phoneNumber;

        public PhoneNumber(String rawPhoneNumber) {
            this.phoneNumber = rawPhoneNumber.replaceAll("[^0-9]","");
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PhoneNumber that = (PhoneNumber) o;
            return Objects.equals(phoneNumber, that.phoneNumber);
        }

        @Override
        public int hashCode() {
            return 0;
        }

        @Override
        public String toString() {
            return "PhoneNumber{" +
                    "phoneNumber='" + phoneNumber + '\'' +
                    '}';
        }

        public static void main(String[] args) {
            System.out.println(new PhoneNumber("010-1234-5678"));
            System.out.println(new PhoneNumber("010 1234 5678"));
            System.out.println(new PhoneNumber("01012345678"));
        }
    }
}
