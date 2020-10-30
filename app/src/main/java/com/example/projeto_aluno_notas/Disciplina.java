package com.example.projeto_aluno_notas;

import android.os.Parcel;
import android.os.Parcelable;

public class Disciplina extends ClassLoader implements Parcelable {

    public String disciplina;
    public Float nota;

    public Disciplina() { }

    public Disciplina(Parcel in) {
        disciplina = in.readString();
        if (in.readByte() == 0) {
            nota = null;
        } else {
            nota = in.readFloat();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(disciplina);
        if (nota == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeFloat(nota);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Disciplina> CREATOR = new Creator<Disciplina>() {
        @Override
        public Disciplina createFromParcel(Parcel in) {
            return new Disciplina(in);
        }

        @Override
        public Disciplina[] newArray(int size) {
            return new Disciplina[size];
        }
    };

    public void setDisciplina(String pDisciplina){
        this.disciplina = pDisciplina;
    }

    public String getDisciplina(){
        return this.disciplina;
    }

    public void setNota(Float pNota){
        this.nota = pNota;
    }

    public Float getNota(){
        return this.nota;
    }
}
