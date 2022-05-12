package games.icebreaker;

import java.util.Set;

public interface IChallenger {
    // A t t e n t i o n :
    // I l n ’ y a p a s f o r c é ment b e s o i n de c o n s t r u c t e u r s
    // dans l a c l a s s e M y C h a l l e n g e r ;
    // t o u t e f o i s s i v o u s d é c i d e z d ’ en é c r i r e , i l f a u d
    // r a o b l i g a t o i r e m e n t qu ’ une v e r s i o n
    // s a n s argument s o i t p r é s e n t e :
    // p u b l i c M y C h a l l e n g e r ( )

    // −−−−−−−−−−−−−−−−−−−−−− F o n c t i o n s pour l e t o u r n o i

    // ∗ L ’ a r b i t r e v o u s demande l e nom de v o t r e é q u i p e .
    // ∗ @ r e t u r n l e nom de v o t r e é q u i p e s o u s l a f o r m e "Nom1
    // − Nom2"
    String teamName();

    // ∗ L ’ a r b i t r e v o u s s i g n a l e v o t r e r ô l e au d é but de l a
    // p a r t i e .
    // ∗ Vous pouv ez p r é p a r e r v o t r e r e p r é s e n t a t i o n i n t e
    // r n e du p l a t e a u à c e moment .
    // ∗ @param r o l e l e r ô l e q u i v o u s e s t a s s i g n é ( "RED" ou
    // "BLACK" )
    void setRole(String role);

    // ∗ L ’ a r b i t r e v o u s s i g n a l e qu ’ i l v a l i d e l e d e r n i
    // e r coup que v o u s l u i a v e z communiqu é .
    // ∗ Vous pouv ez j o u e r c e coup dans v o t r e r e p r é s e n t a t i o n
    // i n t e r n e du p l a t e a u à c e moment .
    // ∗ @param move l e coup que v o u s v e n e z de j o u e r , s o u s l a f o r
    // m e " D2−C2 "
    void iPlay(String move);

    // /∗ ∗
    // ∗ L ’ a r b i t r e v o u s s i g n a l e qu ’ i l v a l i d e l e d e r n i
    // e r coup que l ’ a d v e r s a i r e l u i a v e z communiqu é .
    // ∗ Vous pouv ez j o u e r c e coup dans v o t r e r e p r é s e n t a t i o n
    // i n t e r n e du p l a t e a u à c e moment .
    // ∗ @param move l e coup que l ’ a d v e r s a i r e v i e n s de j o u e r , s
    // o u s l a f o r m e " D2−C2 "
    void otherPlay(String move);

    // La f o n c t i o n bestMove n ’ e s t à i m p l é menter que pour l a p a r t
    // i e 3 du d e v o i r .
    // Pour l a p a r t i e 2 , v o u s pouvez l a l a i s s e r s o u s l a f o r m
    // e :
    // p u b l i c S t r i n g bestMove ( ) {
    // r e t u r n n u l l ;
    // }

    // ∗ L ’ a r b i t r e v o u s demande l e coup que v o u s s o u h a i t e z j
    // o u e r .
    // ∗ C h o i s i s s e z b i e n .
    // ∗ @ r e t u r n l e coup que v o u s comptez j o u e r ( e t que v o u s s o
    // u m e t t e z à l a v a l i d a t i o n de l ’ a r b i t r e ) , s o u s l a
    // f o r m e " D2−C2 "
    String bestMove();

    // ∗ L ’ a r b i t r e v o u s s i g n a l e que v o u s a v e z gagn é .
    // ∗ @ r e t u r n un p e t i t m e s s a g e ou une b a n n i è r e de v i c t
    // o i r e .
    String victory();

    // L ’ a r b i t r e v o u s s i g n a l e que v o u s a v e z p e r d u .
    // ∗ @ r e t u r n un p e t i t m e s s a g e ou une b a n n i è r e de d é f a
    // i t e
    String defeat();

    // ∗ L ’ a r b i t r e v o u s s i g n a l e que l a p a r t i e s ’ e s t s o l
    // d é e p a r une é g a l i t é .
    // ∗ @ r e t u r n un p e t i t m e s s a g e ou une b a n n i è r e de p a r t
    // i e n u l l e
    String tie();

    // ∗ Vous d e v e z r e n v o y e r une cha î ne de c a r a c t è r e s d é c r
    // i v a n t l ’ é t a t du p l a t e a u .
    // ∗ @ r e t u r n l a cha î ne r e p r é s e n t a n t l e p l a t e a u ( v o
    // i r l e s u j e t pour l e f o r m a t u t i l i s é
    String boardToString();

    // ∗ Vous d e v e z m e t t r e à j o u r v o t r e r e p r é s e n t a t i o n
    // i n t e r n e s e l o n l ’ é t a t du p l a t e a u d é c r i t dans un f i
    // c h i e r
    // t e x t e .
    // ∗ @param f i l e N a m e l e nom du f i c h i e r à l i r e ( v o i r l e s u
    // j e t pour l e f o r m a t u t i l i s é
    void setBoardFromFile(String fileName);

    // Vous d e v e z r e n v o y e r l ’ e n s e m b l e d e s c o u p s p o s s i
    // b l e s pour l ’ un d e s j o u e u r s ( d ’ a p r è s l ’ é t a t a c t u e
    // l du
    // p l a t e a u dans v o t r e r e p r é s e n t a t i o n i n t e r n e ) .
    // ∗ @param r o l e l e r ô l e du j o u e u r dont i l f a u t r e n v o y e r
    // l e s c o u p s ( "RED" ou "BLACK" )
    // ∗ @ r e t u r n l ’ e n s e m b l e de c o u p s p o s s i b l e s pour l e j
    // o u e u r d é s i g n é ( s o u s l a f o r m e " D2−C2 " )
    Set<String> possibleMoves(String role);

}
