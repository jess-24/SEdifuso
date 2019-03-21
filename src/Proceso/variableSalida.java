package Proceso;

import java.util.ArrayList;

public class variableSalida {


    int numCompetencias;
    ArrayList<Variable> variables;

    public variableSalida(){

    }

    public variableSalida(int numCompetencias, ArrayList<Variable> variables) {

        this.numCompetencias = numCompetencias;
        this.variables = variables;
    }

    public int getNumCompetencias() {
        return numCompetencias;
    }

    public void setNumCompetencias(int numCompetencias) {
        this.numCompetencias = numCompetencias;
    }

    public ArrayList<Variable> getVariables() {
        return variables;
    }

    public void setVariables(ArrayList<Variable> variables) {
        this.variables = variables;
    }
}
