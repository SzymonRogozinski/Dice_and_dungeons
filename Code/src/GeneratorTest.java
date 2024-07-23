import Equipment.Items.ArmorItem;
import Equipment.Items.ItemQuality;
import Generators.ArmorGenerator;

public class GeneratorTest {

    public static void main(String[] args){
        ItemQuality q = ItemQuality.LEGENDARY;
        int count=10;

        for(int i =0; i<count;i++) {
            ArmorItem v = ArmorGenerator.generateArmor(q);
            int[] s = v.getStats();

            System.out.println(v.name);
            System.out.println("Quality: " + q);
            System.out.println("Tags: " + (v.tags.length == 0 ? "None" : v.tags[0]));
            System.out.println("Body: " + v.getBodyPart());
            System.out.println("Stats");
            System.out.println(">Strength: " + s[0]);
            System.out.println(">Endurance: " + s[1]);
            System.out.println(">Intelligence: " + s[2]);
            System.out.println(">Charisma: " + s[3]);
            System.out.println(">Cunning: " + s[4]);
            System.out.println(">Luck: " + s[5]);
            System.out.println(" ");
        }
    }
}
