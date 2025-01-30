// Со помош на магацини да се дизајнира историјата на пребарување на корисник во еден
// таб од прелистувачот. Системот треба да овозможи:
// - креирање на нов таб со однапред дефинирана почетна страница (“google.com”),
// - отворање на нова страница од тековната положба во историјата на пребарување, така што
// со отворање на нова страница се бришат сите следни страници од историјата, и
// - придвижување нанапред/наназад во историјата на пребарување за n чекори, при што
// доколку бројот на постоечки страници во соодветната насока е помал од n, тогаш
// придвижувањето нанапред/наназад се врши онолку пати колку што е возможно – при
// придвижувањето треба да се отпечати страницата на која се наоѓа корисникот по
// придвижувањето.
// Пример:
// При креирање на нов таб се отпочнува историја во која е сместена google.com информацијата.
// Историјата е:
// google.com, при што текстот во bold покажува дека прелистувачот е тековно на соодветната
// страница.
// Нека следно корисникот ги отвори страниците facebook.com, youtube.com и instagram.com во тој
// редослед. Со тоа историјата на пребарување станува:
// google.com <-> facebook.com <-> youtube.com <-> instagram.com.
// Доколку следно корисникот одлучи да се врати два чекори наназад тогаш би требало да се врати
// на facebook.com, при што остатокот од историјата (youtube.com <-> instagram.com сé уште се чуваат
// како валидни информации) и историјата на пребарување си останува:
// google.com <-> facebook.com <-> youtube.com <-> instagram.com.
// Затоа, ако корисникот одлучи да се придвижи едно место нанапред од facebook.com би требало да
// помине на youtube.com, па историјата е:
// google.com <-> facebook.com <-> youtube.com <-> instagram.com.
// Ако следниот чекор е корисникот да пристапи на нова страница (нека тоа биде linkedin.com), тогаш
// linkedin.com ќе се додаде по youtube.com, а се што претходно било по youtube.com (т.е. во
// примеров Instagram.com) треба да се избрише од историјата на пребарување.
// google.com <-> facebook.com <-> youtube.com <-> linkedin.com.
// Доколку корисникот следно сака да пристапи шест чекори наназад, тоа не е можно па ќе пристапи
// најмногу до google.com, а историјата ќе изгледа:

// google.com <-> facebook.com <-> youtube.com <-> linkedin.com.

import java.util.Stack;

class Browser {
    private Stack<String> back;
    private Stack<String> forward; 
    private String current; 

    
    public Browser() {
        back = new Stack<>();
        forward = new Stack<>();
        current = "google.com";
        System.out.println("Browser initialized. Current page: " + current);
    }

    
    public void openPage(String url) {
        back.push(current);
        current = url; 
        while(!forward.isEmpty()) {
            forward.pop();
        } 
        System.out.println("Opened new page: " + current);
    }

    
    public void goBack(int n) {
        while (!back.isEmpty() && n > 0) {
            forward.push(current); 
            current = back.pop(); 
            n--;
        }
        System.out.println("Current page after going back: " + current);
    }

    
    public void goForward(int n) {
        while (!forward.isEmpty() && n > 0) {
            back.push(current); 
            current = forward.pop();
            n--;
        }
        System.out.println("Current page after going forward: " + current);
    }
}

public class zad24 {
    public static void main(String[] args) {
        Browser tab = new Browser();

        tab.openPage("facebook.com");
        tab.openPage("youtube.com");
        tab.openPage("instagram.com");

        tab.goBack(2); 
        tab.goForward(1); 

        tab.openPage("linkedin.com"); 
        tab.goBack(6); 
    }
}
