package eric.chatservice.ai;

public class Testpeom {
    public static void main(String[] args) {
        Getfive getfive = new Getfive();
        String peom = getfive.getPoem("我爱你");
        peom = peom.replaceAll("[^\u4E00-\u9FA5]", "");
        System.out.println("ok=====================");
        System.out.println(peom);
    }
}
