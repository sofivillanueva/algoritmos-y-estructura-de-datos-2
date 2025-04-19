package TAD.Pila;

public interface IPila {
    void push(int dato);
    
    int pop();
    
    int top();
    
    boolean esta_vacia();
    
    boolean esta_llena();
}
