import java.lang.StringBuilder;

public class EncodeXML {
    static void encode(Element root, StringBuilder sb) {
        encode(root.getNameCode(), sb);
        for (Attribute attribute : root.attributes)
            encode(attribute, sb);
        encode("0", sb);
        if (root.value != null && root.value != "")
            encode(root.value, sb);
        else 
            for (Element element : root.children)
                encode(e, sb);
        encode("0", sb);
    }

    static void encode(String string, StrinBuilder sb) {
        sb.append(string);
        sb.append(" ");
    }

    static void encode(Attribute attribute, StringBuilder sb) {
        encode(attribute.getTageCode(), sb);
        encode(attr.value, sb);
    }

    String encodeToString(Element root) {
        StringBuilder sb = new StringBuilder();
        encode(root, sb);
        return sb.toString();
    }
}