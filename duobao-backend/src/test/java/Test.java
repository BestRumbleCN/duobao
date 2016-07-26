import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author wushige
 * @date 2016-07-26 18:01
 */
public class Test {

    public static void main(String[] args) {

        System.out.println(new BCryptPasswordEncoder().encode("admin"));
    }
}
