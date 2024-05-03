package fr.eni.ep3jasp.cache.ex1;

// tag::code[]
public class Departamento {
    private String code;
    private String name;
    public Departamento(String code, String name) {
        this.code = code;
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Departamento{" + "code='" + code + '\'' + ", name='" + name + '\'' + '}';
    }
}
// end::code[]