package Demo;

public class YTZZAESUtil {

    public static void main(String[] args) throws Exception{
        String source="IJaU0wj3bZsdOAool+YQ6OsyacaXxDDofD3aBpncj8kLePyfesAsJO7AnXKfEcuyNRZMXSQDJnzKftOwTrGufqYlV65YuEzaQ5VUTDXYterx/cOxKXj1FguCZnxBpoRh0BbBBzLiVUY2JIfe0zyX6NDs/jamQYnak+4oqoGVdkM8JMDazhPUPGATfVUkMzyI5xTqMGeGctg4aBD6+r+c+W5LprXKrP5MxcKn3z5u+s6yv7i9Ok/xPObVme3SaOqgH07hcOlK8Cgh4O5Z8f7JbApLeQ1t4dAmQS/OE5Z1AkA2HxnJsg3LBs9uv17m81hyg7qeVMK4594LY7tR5QB/mYORq5M7pVlKBrIJxKwO74/6MoYmiy8/NH7EiyQh4ooTQns7BSqXFoZkG0pILqnbgOspYtOYjYUaBVYbcD27rhaw+1F3BeCbIOuQj/gOedvacMGLlRcEq4HTltjYp+VBIFAZgiNRFLF34ngu+b4iED4m4a3idEL7kKR9r+NKlkglqyfBknv6DS3LmKWDECUm9qbkSW2p7jVBLTWGL323ztM=";
        String re= AesUtil.decrypt(source,"cb3e02f831de454b");
        System.out.println(re);
    }
}
