package com.example.registros;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

//import java.lang.ref.PhantomReference;

public class MainActivity extends AppCompatActivity {
    private EditText et_NombreUDA;
    private EditText et_Profesor;
    private EditText et_Calif;
    private EditText et_Sem;
    private TextView tv_NumReg;
    private RadioButton rb_Aprobada;
    private RadioButton rb_NoAprobada;
    private RadioButton rb_Op;
    private RadioButton rb_Ob;
    private RadioButton rb_1a;
    private RadioButton rb_2a;
    private RadioButton rb_3a;
    private Button btn_GuardarArchivo;
    private Button btn_GuardarReg;
    private Button btn_Calcular;
    private Button btn_CapturarReg;
    private Button btn_Anterior;
    private Button btn_Siguiente;
    private Button btn_LeerArchivo;
    private Lista ListaDeReg = new Lista();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_NombreUDA = findViewById(R.id.et_Uda);
        et_Profesor = findViewById(R.id.et_Profesor);
        et_Profesor = findViewById(R.id.et_Profesor);
        et_Calif = findViewById(R.id.et_Cal);
        et_Sem = findViewById(R.id.et_Semestre);
        tv_NumReg = findViewById(R.id.tv_NumReg);
        rb_Aprobada = findViewById(R.id.rbtn_Aprob);
        rb_NoAprobada = findViewById(R.id.rbtn_NoAprob);
        rb_Op = findViewById(R.id.rbtn_Op);
        rb_Ob = findViewById(R.id.rbtn_Ob);
        rb_1a = findViewById(R.id.rbtn_Opor1);
        rb_2a = findViewById(R.id.rbtn_Opor2);
        rb_3a = findViewById(R.id.rbtn_Opor3);

        btn_GuardarArchivo = findViewById(R.id.btn_SaveFile);
        btn_GuardarReg = findViewById(R.id.btn_SaveReg);
        btn_CapturarReg = findViewById(R.id.btn_CapturarReg);
        btn_Calcular = findViewById(R.id.btn_Cal);

        btn_Siguiente = findViewById(R.id.btn_Siguiente);
        btn_Anterior = findViewById(R.id.btn_Anterior);
        btn_LeerArchivo = findViewById(R.id.btn_Leer);

        et_NombreUDA.setEnabled(false);
        et_Profesor.setEnabled(false);
        et_Calif.setEnabled(false);
        et_Sem.setEnabled(false);
        btn_GuardarArchivo.setEnabled(false);
        btn_GuardarReg.setEnabled(false);
        btn_Calcular.setEnabled(false);

        rb_Aprobada.setEnabled(false);
        rb_NoAprobada.setEnabled(false);
        rb_Op.setEnabled(false);
        rb_Ob.setEnabled(false);
        rb_1a.setEnabled(false);
        rb_2a.setEnabled(false);
        rb_3a.setEnabled(false);
    }

    public void btn_Capturar_Registro(View view){
        et_NombreUDA.setText("");
        et_Profesor.setText("");
        et_Calif.setText("");
        et_Sem.setText("");
        tv_NumReg.setText("");

        et_NombreUDA.setEnabled(true);
        et_Profesor.setEnabled(true);
        et_Calif.setEnabled(true);
        et_Sem.setEnabled(true);
        btn_GuardarReg.setEnabled(true);

        btn_CapturarReg.setEnabled(false);

        rb_Op.setEnabled(true);
        rb_Ob.setEnabled(true);
        rb_1a.setEnabled(true);
        rb_2a.setEnabled(true);
        rb_3a.setEnabled(true);

        btn_Siguiente.setEnabled(false);
        btn_Anterior.setEnabled(false);
        btn_LeerArchivo.setEnabled(false);

    }

    public void btn_Guardar_Registro(View view){
        registroUDA UDA;
        UDA = new registroUDA();
        String aux;
        Float calif;

        UDA.setId(16);

        aux = et_NombreUDA.getText().toString();
        UDA.setNombre(aux);
        aux = et_Profesor.getText().toString();
        UDA.setProfesor(aux);
        aux = et_Calif.getText().toString();
        calif = Float.parseFloat(aux);
        aux = et_Sem.getText().toString();
        UDA.setSem(Byte.parseByte(aux));

        if (calif < 7.0f){
            UDA.setAprobado(false);
            rb_NoAprobada.setChecked(true);
        }
        else{
            UDA.setAprobado(true);
            rb_Aprobada.setChecked(true);
        }


        if (rb_Aprobada.isChecked())
            UDA.setAprobado(true);
        else if (rb_NoAprobada.isChecked())
            UDA.setAprobado(false);

        if (rb_Ob.isChecked())
            UDA.setObligatoria(true);
        else if (rb_Op.isChecked())
            UDA.setObligatoria(false);

        if (rb_1a.isChecked())
            UDA.setSem((byte) 1);
        else if (rb_2a.isChecked())
            UDA.setSem((byte) 2);
        else if (rb_3a.isChecked())
            UDA.setSem((byte) 3);

        //UDA.displayRegistro();
        ListaDeReg.InsertarNodo(UDA);

        btn_CapturarReg.setEnabled(true);
        btn_GuardarReg.setEnabled(false);

        btn_Siguiente.setEnabled(true);
        btn_Anterior.setEnabled(true);
        btn_LeerArchivo.setEnabled(true);

        et_NombreUDA.setEnabled(false);
        et_Profesor.setEnabled(false);
        et_Calif.setEnabled(false);
        et_Sem.setEnabled(false);
        btn_GuardarArchivo.setEnabled(false);
        btn_GuardarReg.setEnabled(false);
        btn_Calcular.setEnabled(false);

        rb_Aprobada.setEnabled(false);
        rb_NoAprobada.setEnabled(false);
        rb_Op.setEnabled(false);
        rb_Ob.setEnabled(false);
        rb_1a.setEnabled(false);
        rb_2a.setEnabled(false);
        rb_3a.setEnabled(false);
        System.out.println("Numero de registros: " + ListaDeReg.getNunElem());
    }

    public void btn_Anterior(View view){

    }

    public void btn_Siguiente(View view){
        Nodo nodoAux;
        registroUDA UDA;
        if( ListaDeReg.getNodoEnPantalla()!=null ){

        }
        else{ //No hay nodo en pantalla
            nodoAux = ListaDeReg.getInicio();
            if ( nodoAux!=null ) { //verificar que la lista no esta vacia
                ListaDeReg.setNodoEnPantalla(nodoAux);
                UDA = nodoAux.getUDA();

                tv_NumReg.setText(String.valueOf( UDA.getId() ));

                et_NombreUDA.setText(UDA.getNombre());
                et_Profesor.setText(UDA.getProfesor());
                et_Calif.setText(String.valueOf(UDA.getCalFinal()));
                et_Sem.setText(String.valueOf(UDA.getSem()));

                if ( UDA.getAprobado() )
                    rb_Aprobada.setChecked(true);
                else
                    rb_NoAprobada.setChecked(true);


                if (UDA.getObligatoria())
                    rb_Ob.setChecked(true);
                else
                    rb_Op.setChecked(true);

                if (UDA.getNumOp() == 1)
                    rb_1a.setChecked(true);
                else if (UDA.getNumOp() == 2)
                    rb_2a.setChecked(true);
                else if (UDA.getNumOp() == 3)
                    rb_3a.setChecked(true);

            }
            else{ //lista vacia
                et_NombreUDA.setText("Sin registros");
                et_Profesor.setText("Sin registros");
                et_Sem.setText("S/R");
                et_Calif.setText("S/R");

            }
        }

    }


} //fin de la clase MAIN_ACTIVITY

class registroUDA{
    private int id;
    private String nombre;
    private String profesor;
    private float calFinal;
    private byte sem;
    private boolean aprobado;
    private boolean obligatoria;
    private byte numOp;

    registroUDA(){
        id = 0;
        nombre = null;
        profesor = null;
        calFinal = 0;
        sem = 0;
        aprobado = false;
        obligatoria = false;
        numOp = 0;
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public void setCalFinal(float calFinal) {
        this.calFinal = calFinal;
    }

    public void setSem(byte sem) {
        this.sem = sem;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    public void setObligatoria(boolean obligatoria) {
        this.obligatoria = obligatoria;
    }

    public void setNumOp(byte numOp) {
        this.numOp = numOp;
    }

    //getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getProfesor() {
        return profesor;
    }

    public byte getNumOp() {
        return numOp;
    }

    public float getCalFinal() {
        return calFinal;
    }

    public byte getSem() {
        return sem;
    }

    public  boolean getAprobado(){ return  aprobado; }

    public  boolean getObligatoria(){ return  obligatoria; }

    public void displayRegistro(){
        System.out.println("Id: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Profesor: " + profesor);
        System.out.println("Calificacion: " + calFinal);
        System.out.println("Semestre: " + sem);
        if (aprobado)
            System.out.println("aprobado");
        else
            System.out.println("reprobado");
        if (obligatoria)
            System.out.println("obligarotio ");
        else
            System.out.println("obtativa " );
        System.out.println("NO.Oportunidad: " + numOp);
    }

}//fin de la clase registroUDA

class Nodo{
    private registroUDA UDA;
    private Nodo sig;
    private Nodo prev;

    Nodo(registroUDA dato, Nodo prev, Nodo sig){
        this.UDA = dato;
        this.sig = sig;
        this.prev = prev;
    }

    //getters
    public registroUDA getUDA() {
        return UDA;
    }

    public Nodo getPrev() {
        return prev;
    }

    public Nodo getSig() {
        return sig;
    }

    //setters
    public void setUDA(registroUDA UDA) {
        this.UDA = UDA;
    }

    public void setPrev(Nodo prev) {
        this.prev = prev;
    }

    public void setSig(Nodo sig) {
        this.sig = sig;
    }
}//FIN DE LA CLASE NODO

class Lista{
    private Nodo inicio;
    private int nunElem;
    private Nodo NodoEnPantalla;

    Lista(){
        this.inicio = null;
        this.nunElem = 0;
    }

    //getters lista()
    public Nodo getInicio() {
        return inicio;
    }

    public int getNunElem() {
        return nunElem;
    }

    public Nodo getNodoEnPantalla() {
        return NodoEnPantalla;
    }

    //setters lista()
    public void setInicio(Nodo inicio) {
        this.inicio = inicio;
    }

    public void setNunElem(int nunElem) {
        this.nunElem = nunElem;
    }

    public void setNodoEnPantalla(Nodo nodoEnPantalla) {
        NodoEnPantalla = nodoEnPantalla;
    }

    //intertar nodo registroUDA
    public void InsertarNodo(registroUDA UDA){
        Nodo NewNodo = new Nodo(UDA, null, null);
        Nodo aux;
        //si la lista esta vacia
        if (this.inicio == null)
            this.inicio = NewNodo;
        else { //la lista no esta vacia
            aux = this.inicio;
            while ( aux.getSig()!=null )
                aux = aux.getSig();

            //aux al final del ciclo contiene el acceso al ultimo nodo
            aux.setSig(NewNodo);
            NewNodo.setPrev(aux);

        }
        this.nunElem++;
    }
}//fin de la clase Lista