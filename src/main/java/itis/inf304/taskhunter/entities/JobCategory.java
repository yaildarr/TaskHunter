package itis.inf304.taskhunter.entities;

public class JobCategory {
    private int id;
    private String name;
    public JobCategory(int id) {
        this.id = id;
        switch (id){
            case 1:
                this.name = "Доставка";
                break;
            case 2:
                this.name = "Услуги по дому";
                break;
            case 3:
                this.name = "Образование";
                break;
            case 4:
                this.name = "Творческие услуги";
                break;
            case 5:
                this.name = "События";
                break;
            case 6:
                this.name = "Технические услуги";
                break;
            case 7:
                this.name = "Спортивные услуги";
                break;
            default:
                this.name = "Неизвестная категория";
                break;
        }
    }
    public JobCategory(int id,String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
