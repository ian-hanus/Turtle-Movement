public interface VariableDisplay {
    default public void addVariable(Variable v){
    }

    default public void setValue(String varName){
    }

    default public Variable removeVariable(String varName){
        return null;
    }

    default public void getValue(String varName){
    }

}
