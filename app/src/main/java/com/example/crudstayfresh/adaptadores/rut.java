package com.example.crudstayfresh.adaptadores;

/**
 * Clase encargada de formatear el rut con puntos y guiones,
 *  además de validarlo con su dígito verificador.
 * @author Danilo
 */
public class rut {

    /**
     * Le da formato a un rut, concatenándole puntos y guión.
     * @param rut Rut a formatear.
     * @return Un nuevo String, con el rut formateado.
     */
    static public String formatear(String rut){
        int cont=0;
        String format;
        if(rut.length() == 0){
            return "";
        }else{
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            format = "-"+rut.substring(rut.length()-1);
            for(int i = rut.length()-2;i>=0;i--){
                format = rut.substring(i, i+1)+format;
                cont++;
                if(cont == 3 && i != 0){
                    format = "."+format;
                    cont = 0;
                }
            }
            return format;
        }
    }

    /**
     * Valida un rut de acuerdo a su dígito verificador.
     * @param rut Rut a validar
     * @return true si el rut es válido,
     * false en cualquier otro caso.
     */
    static public boolean validar(String rut){
        int suma=0;
        String dvR,dvT;
        int[] serie = {2,3,4,5,6,7};
        rut = rut.replace(".", "");
        rut = rut.replace("-", "");
        dvR = rut.substring(rut.length()-1);
        for(int i = rut.length()-2;i>=0; i--){
            suma +=  Integer.valueOf(rut.substring(i, i+1))
                    *serie[(rut.length()-2-i)%6];
        }
        dvT=String.valueOf(11-suma%11);
        if(dvT.compareToIgnoreCase("10") == 0){
            dvT = "K";
        }

        if(dvT.compareToIgnoreCase(dvR) == 0){
            return true;
        } else {
            return false;
        }
    }
}
