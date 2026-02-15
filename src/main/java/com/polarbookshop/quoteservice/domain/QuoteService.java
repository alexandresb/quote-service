package com.polarbookshop.quoteservice.domain;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;

@Service
public class QuoteService {
    private final Random random = new Random();
    //liste en mémoire de citations
    private final List<Quote> quotes = List.of(
            new Quote("Content A", "Abigail", Genre.ADVENTURE),
            new Quote("Content B", "Beatrix", Genre.ADVENTURE),
            new Quote("Content C", "Casper", Genre.FANTASY),
            new Quote("Content D", "Dobby", Genre.FANTASY),
            new Quote("Content E", "Eileen", Genre.SCIENCE_FICTION),
            new Quote("Content F", "Flora", Genre.SCIENCE_FICTION)
    );

    //retourne toutes les citations
    public Flux<Quote> getAllQuotes(){
        return Flux.fromIterable(quotes);
    }

    //retourne une citation au hasard
    public Mono<Quote> getRandomQuote(){
        return Mono.just(quotes.get(random.nextInt(quotes.size() -1)));
    }

    //retourne une citation au hasard pour un genre donné
    public Mono<Quote> getRandomQuoteByGenre(Genre genre){
        //retour d'une liste filtrée par genre
        var quotesForGenre = quotes.stream()
                .filter(q->q.genre().equals(genre))
                .toList();
        //retour d'une citation random pour un genre donnée
        return Mono.just(quotesForGenre.get(random.nextInt(quotesForGenre.size() -1)));
    }

}
