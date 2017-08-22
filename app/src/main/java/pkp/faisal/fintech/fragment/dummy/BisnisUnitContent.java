package pkp.faisal.fintech.fragment.dummy;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for providing sample name for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class BisnisUnitContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<BisnisUnit> ITEMS = new ArrayList<BisnisUnit>();


    static {
        // Add some sample items.

        ITEMS.add(new BisnisUnit(
                "1", "Pinjaman Uang", "Opsi untuk meminjam dana"
        ));


        ITEMS.add(new BisnisUnit(
                "2", "Kredit Tanpa Anggunan", "Opsi untuk kredit tanpa anggunan"
        ));
    }


    /**
     * A dummy item representing a piece of name.
     */
    public static class BisnisUnit {
        public final String id;
        public final String name;
        public final String details;
        public Boolean selected;

        public BisnisUnit(
                String id,
                String content,
                String details) {
            this.id = id;
            this.name = content;
            this.details = details;
            this.selected = false;
        }
    }
}
