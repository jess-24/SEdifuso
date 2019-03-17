package Proceso;

public class Evaluar
{

public static void Evaluer(int entrara_real)
{
    int PC1=1,PC2=1,PC3=1;
    int primera_etiqueda=0,ultima_etiqueta=0;
    int entrada_real=0;

    String[] puntoss = Consultar_archivo_PC();



    if(entrada_real==PC1)
    {
        PC1=1;
        PC2=0;
        PC3=0;
    }
	else
    {
        if (entrada_real==PC2)
        {
            PC1=0;
            PC2=1;
            PC3=0;
        }
        else
        {
            if (entrada_real==PC3)
            {
                PC1=0;
                PC2=0;
                PC3=1;
            }
            else
            {
                if (entrada_real<= primera_etiqueda)
                {
                PC1=1;
                PC2=0;
                PC3=3;
                }
				else
                {
                    if (entrada_real>= ultima_etiqueta)
                    {
                    PC1=0;
                    PC2=0;
                    PC3=1;
                    }
                }
            }
        }
    }
}//metodo
}//class