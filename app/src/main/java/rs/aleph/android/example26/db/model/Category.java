package rs.aleph.android.example26.db.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by milossimic on 11/6/16.
 */
@DatabaseTable(tableName = Category.TABLE_NAME_USERS)
public class Category {

    public static final String TABLE_NAME_USERS = "categories";

    public static final String FIELD_NAME_ID     = "id";
    public static final String FIELD_NAME_NAME   = "name";
    public static final String FIELD_NAME_IMAGE   = "image";

    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true)
    private int id;

    @DatabaseField(columnName = FIELD_NAME_NAME)
    private String name;

    @DatabaseField(columnName = FIELD_NAME_IMAGE)
    private String image;

    //ORMLite zahteva prazan konstuktur u klasama koje opisuju tabele u bazi!
    public Category() {
    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return name;
    }
}