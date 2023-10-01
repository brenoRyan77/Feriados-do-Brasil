# Projeto Feriados do Brasil com JollDay

Este é um projeto de exemplo que demonstra o uso da biblioteca Jollyday para lidar com feriados em Java.

## Objetivo do Projeto

O objetivo deste projeto é mostrar como usar a biblioteca Jollyday para verificar feriados em um aplicativo Java e ajustar datas de vencimento para o próximo dia útil, caso necessário. A biblioteca Jollyday oferece suporte a vários países, incluindo o Brasil, e fornece informações detalhadas sobre feriados nacionais e regionais.

## Como Usar a Biblioteca Jollyday

Aqui estão as etapas para começar a usar a biblioteca Jollyday em seu projeto Java:

1. **Adicionar as Dependências**:

   - Usando Maven: Certifique-se de adicionar as dependências do Jollyday e do JAXB ao arquivo `pom.xml` do seu projeto Maven.

     ```xml
     <dependencies>
         <dependency>
             <groupId>de.jollyday</groupId>
             <artifactId>jollyday</artifactId>
             <version>0.5.1</version> <!-- Verifique a versão mais recente no Maven Central -->
         </dependency>
         <dependency>
             <groupId>jakarta.xml.bind</groupId>
             <artifactId>jakarta.xml.bind-api</artifactId>
             <version>2.3.3</version>
         </dependency>
         <dependency>
             <groupId>org.glassfish.jaxb</groupId>
             <artifactId>jaxb-runtime</artifactId>
             <version>2.3.4</version>
         </dependency>
     </dependencies>
     ```

   - Usando Gradle: Adicione as dependências do Jollyday, do JAXB e do JAXB Runtime ao arquivo `build.gradle` do seu projeto Gradle.

     ```gradle
     dependencies {
         implementation 'de.jollyday:jollyday:0.5.1' // Verifique a versão mais recente no Maven Central
         implementation 'jakarta.xml.bind:jakarta.xml.bind-api:2.3.3'
         implementation 'org.glassfish.jaxb:jaxb-runtime:2.3.4'
     }
     ```

  ## Exemplo de Código Completo

Aqui está um exemplo completo de código que demonstra como usar a biblioteca Jollyday para verificar feriados e ajustar datas de vencimento. A implementação completa pode ser encontrada na classe `Main` no [repositório deste projeto](https://github.com/brenoRyan77/Feriados-do-Brasil/blob/master/src/main/java/com/br/brenoryan77/holidays/Main.java).
```java
import de.jollyday.Holiday;
import de.jollyday.HolidayCalendar;
import de.jollyday.HolidayManager;

import java.time.LocalDate;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        // Criar um HolidayManager para o calendário brasileiro
        HolidayManager holidayManager = HolidayManager.getInstance(HolidayCalendar.BRAZIL);

        // Obter a lista de feriados para um ano específico
        int anoDesejado = 2023;
        Set<Holiday> feriados = holidayManager.getHolidays(anoDesejado);

        // Data de vencimento de exemplo: 29 de setembro de 2023 (sexta-feira)
        LocalDate dataVencimento = LocalDate.of(2023, 9, 29);

        // Verificar se a data de vencimento é um feriado ou final de semana
        if (isFeriado(dataVencimento, feriados) || dataVencimento.getDayOfWeek().getValue() > 5) {
            dataVencimento = ajustarDataVencimento(dataVencimento, feriados);
        }

        // Imprimir a data de vencimento ajustada
        System.out.println("Data de vencimento ajustada: " + dataVencimento);
    }

    public static boolean isFeriado(LocalDate data, Set<Holiday> feriados) {
        return feriados.stream().anyMatch(feriado -> feriado.getDate().equals(data));
    }

    public static LocalDate ajustarDataVencimento(LocalDate dataVencimento, Set<Holiday> feriados) {
        LocalDate novaData = dataVencimento;
        while (isFeriado(novaData, feriados) || novaData.getDayOfWeek().getValue() > 5) {
            novaData = novaData.plusDays(1);
        }
        return novaData;
    }
}
```

# Instruções de Compilação e Execução
  - Clone este repositório em sua máquina local.
  - Configure as dependências no seu ambiente de desenvolvimento.
  - Compile o código-fonte.
  - Execute o aplicativo.

# Créditos

Este projeto utiliza a biblioteca Jollyday para lidar com feriados. O Jollyday é uma biblioteca de código aberto criada por [Sven Diedrichsen](https://github.com/svendiedrichsen/jollyday/tree/master) e fornece suporte para feriados de vários países.

Agradecemos ao [repositório Jollyday no GitHub](https://github.com/svendiedrichsen/jollyday/tree/master) por fornecer uma excelente biblioteca que torna mais fácil a manipulação de feriados em projetos Java.
