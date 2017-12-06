package DatabasePrimitive;


/**
 * Created by Наталья on 03.07.2017.
 */
    import java.util.Random;

    public class RandomText {
        public RandomText() {
        }

        public String CreatePassword(int length) {
            String valid = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
            String res = "";

            for(Random rnd = new Random(); 0 < length--; res = res + valid.charAt(rnd.nextInt(valid.length()))) {
                ;
            }

            return res;
        }
    }
