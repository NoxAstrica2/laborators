package labs.lab2;


import java.util.Objects;

public class Flat {
    private FlatType type;
    private String number;
    private Person owner;

    private Flat(FlatType type, String number, Person owner) {
        this.type = type;
        this.number = number;
        this.owner = owner;
    }

    public FlatType getType() {
        return type;
    }

    public void setType(FlatType type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flat flat = (Flat) o;
        return owner.getPIN().equals(flat.owner.getPIN()) && type.title().equals(flat.type.title()) && number.equals(flat.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, number, owner);
    }

    @Override
    public String toString() {
        return "Flat{" +
                "type=" + type +
                ", number=" + number +
                ", owner=" + owner +
                '}';
    }

    public static class FlatBuilder {
        private FlatType type;
        private String number;
        private Person owner;

        public FlatBuilder(String number) {
            this.number = number;
        }

        public FlatBuilder type(FlatType type) {
            this.type = type;
            return this;
        }

        public FlatBuilder owner(Person owner) {
            this.owner = owner;
            return this;
        }

        public Flat build() {
            return new Flat(this.type, this.number, this.owner);
        }
    }

}
