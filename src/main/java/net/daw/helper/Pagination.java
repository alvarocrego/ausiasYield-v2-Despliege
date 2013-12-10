
package net.daw.helper;

import java.util.ArrayList;

/**
 *
 * @author rafa
 */
public class Pagination {

    public static ArrayList<String> getButtonPad(String strLink, int intPageNumber, int intTotalPages, int intNeighborhood) throws Exception {
        ArrayList<String> vector = new ArrayList<>();
        if (intPageNumber > 0 && intTotalPages > 0) {
            if (intPageNumber > 1) {
                vector.add("<li>" + strLink + Integer.toString(intPageNumber - 1) + "\">prev</a></li>");
            }
            if (intPageNumber > intNeighborhood + 1) {
                vector.add("<li>" + strLink + "1\">1</a></li>");
            }
            if (intPageNumber > intNeighborhood + 2) {
                vector.add("<li>" + "<a href=\"#\">...</a>" + "</li>");
            }
            for (int i = intPageNumber - intNeighborhood; i <= intPageNumber + intNeighborhood; i++) {
                if (i >= 1 && i <= intTotalPages) {
                    if (intPageNumber == i) {
                        vector.add("<li class=\"active\">" + strLink + Integer.toString(i) + "\">" + i + "</a></li>");
                    } else {
                        vector.add("<li>" + strLink + Integer.toString(i) + "\">" + i + "</a></li>");
                    }
                }
            }
            if (intPageNumber < intTotalPages - (intNeighborhood + 1)) {
                vector.add("<li>" + "<a href=\"#\">...</a>" + "</li>");
            }
            if (intPageNumber < intTotalPages - (intNeighborhood)) {
                vector.add("<li>" + strLink + Integer.toString(intTotalPages) + "\">" + Integer.toString(intTotalPages) + "</a></li>");
            }
            if (intPageNumber < intTotalPages) {
                vector.add("<li>" + strLink + Integer.toString(intPageNumber + 1) + "\">next</a></li>");
            }

            String temp = vector.get(0);
            temp = "<div class=\"pagination\"><ul>" + temp;
            vector.remove(0);
            vector.add(0, temp);

            temp = vector.get(vector.size() - 1);
            temp += "</ul></div>";
            vector.remove(vector.size() - 1);
            vector.add(temp);
        }
        return vector;
    }
}
