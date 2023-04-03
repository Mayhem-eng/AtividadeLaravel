import com.company.Conversoes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Double.isNaN;

public class Bhaskara {

    public static void main(String[] args) {

        /* Lista que pega os valores informados pelo usuario
        e converte eles em Double, com fim realizar os calculos
        */
        List<Double> getValuesDouble = new ArrayList<>();

        /* Lista do tipo JLabel que tem 3 do mesmo instanciadas
        cada uma relacionado a um tipo diferente de
        resposta
        */
        List<JLabel> resultadosFinais = Arrays.asList(new JLabel("Delta: "), new JLabel("RaizX: "), new JLabel("RaizY: "));

        // Classe com métodos de conversões de tipos
        Conversoes converter = new Conversoes();

        // Frame pai de toda a aplicação
        JFrame frame = new JFrame("Formula de Bhaskara");

        /* Painel que está dentro do frama e que organizará todos os
        componentes gráficos */
        JPanel panel = new JPanel(new GridBagLayout());

        JButton calculeDelta = new JButton("Calcular Delta: ");

        /*Campo que irá armazenar 3 tipos de respostas: Delta, raiz1 e raiz2*/
        JTextArea resultadosCalculo = new JTextArea();
        resultadosCalculo.setBackground(Color.LIGHT_GRAY);

        /* Criando uma List instanciando 3 JLabels, cada um irá setar o tipo de valor
        que o usuário irá colocar
        */
        List<JLabel> textos = Arrays.asList(new JLabel("Valor de A: "), new JLabel("Valor de B: "), new JLabel("Valor de C: "));
        /* Criando uma List instanciando 3 JTextField, cada um dará uma caixa de texto
        baseado no JLabel para o usuario por
        */
        List<JTextField> caixasDeTexto = Arrays.asList(new JTextField(), new JTextField(), new JTextField());
        /*Ao iterar sobre cada um dos 3 JTextField iremos configurar o tamanho da caixa*/
        for(int x = 0; x < caixasDeTexto.size(); x++){
            caixasDeTexto.get(x).setPreferredSize(new Dimension(140, caixasDeTexto.get(x).getPreferredSize().height));
        }

        GridBagConstraints gbc  = new GridBagConstraints();

        /* Configurando o titulo */
        /*Definindo-os na linha 0 e coluna 0 do GridBagConstraints*/
        gbc.gridx = 0;
        gbc.gridy = 0;
        /*Fazendo ele ocupar duas colunas*/
        gbc.anchor = GridBagConstraints.CENTER;

        /*Setando o texto e configurando a fonte e tamanho dela*/
        JLabel textoBhaskara = new JLabel("Fórmula de Bhaskara");
        textoBhaskara.setFont(new Font("Arial",Font.PLAIN, 25 ));
        panel.add(textoBhaskara, gbc);

        /*Basicamente a mesma coisa o Titulo, mas com Sub-titulo agora
        * e deixando ele na linha debaixo do titulo*/
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 10, 0);
        JLabel subTitulo = new JLabel("ax² + bx + c = 0");
        subTitulo.setFont(new Font("Arial",Font.PLAIN, 16 ));
        panel.add(subTitulo, gbc);

        // Adicionando a caixa de texto do valor A
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 34);
        panel.add(textos.get(0), gbc);

        gbc.anchor = GridBagConstraints.EAST;
        panel.add(caixasDeTexto.get(0), gbc);

        // Adicionando a caixa de texto do valor B
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 34);
        panel.add(textos.get(1), gbc);

        gbc.anchor = GridBagConstraints.EAST;
        panel.add(caixasDeTexto.get(1), gbc);

        // Adicionando a caixa de texto do valor C
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 34);
        panel.add(textos.get(2), gbc);

        gbc.anchor = GridBagConstraints.EAST;
        panel.add(caixasDeTexto.get(2), gbc);

        calculeDelta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == calculeDelta){
                    double delta = 0;
                    double raiz1 = 0;
                    double raiz2 = 0;

                    List<String> getingValues = Arrays.asList(
                            caixasDeTexto.get(0).getText(),
                            caixasDeTexto.get(1).getText(),
                            caixasDeTexto.get(2).getText()
                    );

                    try{

                        for(int x = 0; x < getingValues.size(); x++){
                            getValuesDouble.add(converter.StringToDouble(getingValues.get(x)));
                        }
                    }catch (NumberFormatException ex){
                        resultadosCalculo.setText("Algum dos valores informados" +
                                "\nnão é do tipo númerico!!");
                    }

                    try{
                        delta = (Math.pow(getValuesDouble.get(1), 2) - (4 * getValuesDouble.get(0) * getValuesDouble.get(2)));
                        if(delta > 0.0){
                            raiz1 = (getValuesDouble.get(1) + Math.sqrt(delta)) / (2 * getValuesDouble.get(0));
                            raiz2 = (getValuesDouble.get(1) - Math.sqrt(delta)) / (2 * getValuesDouble.get(0));
                            resultadosCalculo.append(converter.DoubleToString(delta)
                                    + "\n" + converter.DoubleToString(raiz1)
                                    + "\n" + converter.DoubleToString(raiz2));
                            for(JLabel value : resultadosFinais){
                                value.setVisible(true);
                            }
                        }
                        else if(delta == 0.0){

                            raiz1 = getValuesDouble.get(1) / (2 * getValuesDouble.get(0));
                            if(isNaN(raiz1)){
                                raiz1 = 0.0;
                            }
                            resultadosCalculo.append(converter.DoubleToString(delta)
                                    + "\n" + converter.DoubleToString(raiz1)
                                    + "\n" + "  - ");
                            for(JLabel value : resultadosFinais){
                                value.setVisible(true);
                            }

                        }
                        else{
                            resultadosCalculo.setText("Não há raizes reais nessa equação");
                        }
                    }catch (IndexOutOfBoundsException iobe){
                        resultadosCalculo.setText("Dados informados não são do tipo númerico");
                    }

                    resultadosCalculo.setVisible(true);

                    calculeDelta.setVisible(false);
                }
              }
        });

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 13, 0, 0);
        panel.add(calculeDelta, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 25, 0, 10);
        panel.add(resultadosCalculo, gbc);
        resultadosCalculo.setVisible(false);


        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(-20, 25, 0, 10);
        panel.add(resultadosFinais.get(0), gbc);

        gbc.insets = new Insets(12, 25, 0, 10);
        panel.add(resultadosFinais.get(1), gbc);

        gbc.insets = new Insets(44, 25, 0, 10);
        panel.add(resultadosFinais.get(2), gbc);

        for(JLabel value : resultadosFinais){
            value.setVisible(false);
        }

        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.getContentPane().add(panel);
        frame.setVisible(true);

    }
}
