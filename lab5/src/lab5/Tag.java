package lab5;

import java.util.ArrayList;


/**
 * Класс {@link Tag} содержит название тега {@link #name}, строковый контент (если он есть) {@link #content},
 * массив из тэгов-потомков {@link #children} и тэг-родитель {@link #parent}.
 */
public class Tag {

    /**
     * Название тэга, например, "band"
     */
    public String name;

    public String content;

    public ArrayList<Tag> children;

    public Tag parent;


    /**
     * @param name название тэга, например, "band"
     * @param parent объект родительского тэга. Null, если это корневой тэг
     */
    public Tag(String name, Tag parent) {
        this.name = name;
        if(parent != null){
            parent.getChildren().add(this);
        }
        this.parent = parent;
        children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<Tag> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Tag> children) {
        this.children = children;
    }
}
