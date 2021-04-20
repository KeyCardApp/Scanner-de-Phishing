package com.phishing.scanner.phiscanner;

import java.util.Stack;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ScannerController {

    // palavras e pontos
    public String[] words = { "vencer", "instantâneo", "cartão de crédito", "rendimento", "cupom", "natural",
            "felicitações", "desconto", "oferta", "dinheiro", "viagra", "drástico", "financeiro", "promoção", "rápido",
            "garantia", "gratuito", "membro", "segredo", "truque", "crescimento", "Imperdível", "sexo", "ejaculação",
            "ajuda", "aproveite", "parceiro", "gordo", "lucro", "investimento", "golpe" };

    public int[] scores = { 3, 7, 10, 2, 7, 5, 3, 10, 8, 6, 10, 5, 4, 8, 3, 4, 5, 2, 8, 5, 6, 5, 10, 6, 4, 4, 6, 8, 3,
            5, 10 };

    // pontuacao final
    public int score = 0;

    // palavras suspeitas
    // public String[] suspeitas;
    public Stack<String> suspeitas = new Stack<String>();

    @GetMapping("/teste")
    public String teste(Model model) {
        model.addAttribute("scanner", new ScannerPhi());
        return "teste";
    }

    @GetMapping("/scanner")
    public String form(Model model) {
        model.addAttribute("scanner", new ScannerPhi());
        return "scanner";
    }

    @PostMapping("/scanner")
    public String result(@ModelAttribute ScannerPhi scanner, Model model) {
        scanner.setScore(0);
        score = 0;

        // pega o conteudo do email
        String email = scanner.getContent();

        // separa as palavras
        String separado[] = email.split(" ");

        // coloca tudo em minusculo
        int count = 0;
        for (String palavra : separado) {
            separado[count] = palavra.toLowerCase();
            count++;
        }

        // limpar tudo de espaco pontos e virgulas
        count = 0;
        for (String palavra : separado) {
            separado[count] = palavra.replaceAll(" ", "");
            separado[count] = palavra.replaceAll(".", "");
            separado[count] = palavra.replaceAll(",", "");
            separado[count] = palavra.replaceAll(" ", "");
            count++;
        }

        // contar pontos
        for (int i = 0; i < separado.length; i++) {
            for (int j = 0; j < words.length; j++) {

                char aux[] = separado[i].toCharArray();
                char aux2[] = words[j].toCharArray();
                boolean eigual = false;

                int count2 = 0;
                for (char c : aux) {
                    if (c == aux2[count2]) {
                        eigual = true;
                    } else {
                        eigual = false;
                        break;
                    }
                    count2++;
                }
                if (eigual) {
                    if (aux.length > 2) {
                        score = score + scores[j];
                        // System.out.println(separado[i]);
                        suspeitas.push(separado[i]);
                    }

                }
            }
        }

        // setar pontuacao e chamar nova tela
        System.out.println(suspeitas);
        scanner.setSuspeitas(suspeitas);
        scanner.setScore(score);
        model.addAttribute("scanner", scanner);
        return "result";
    }

}