package rj.cefet.sacapi.enums;

public enum Status {
    FECHADO(0),EM_ANDAMENTO(1),ABERTO(2),RETORNADO(3);
    public final int value;
    Status(int value){
        this.value = value;
    }
}
