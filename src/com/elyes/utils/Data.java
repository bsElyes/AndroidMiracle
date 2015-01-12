package com.elyes.utils;
//package com.esprit.utils;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.esprit.entities.Produit;
//import com.esprit.miracle.R;
//
//
//public class Data 
//{
//	public static String[]nom ={"Lady Million Eau My Gold",
//								"Armani Code Femme Ultimate",
//								"By Terry Baume De Rose Nutri",
//								"Armani Code Ultimate",
//								"One Million",								
//								};
//	public static String[]description ={"Cette nouvelle eau de toilette fruitée florale s'ouvre sur un départ étourdissant de notes fruitées de mangue, et de pamplemousse, rehaussé d’essence de Néroli, qui contraste avec la douceur et la chaleur du cèdre en fond, révélant un sentiment de frénésie et de doux laisser-aller.",
//										"L'inspiration d'Armani Code Femme Ultimate… un rêve… Le rêve de Monsieur Armani : celui d'une fleur imaginaire, qui sculpte la lumière.Le contraste d'ombre et de lumière créé par cette broderie fleurie révèle le jeu de mystère et de séduction d’une femme sensuelle et glamour, fatale et irrésistible.",
//										"Version teintée du cultissime Baume de Rose, ce soin intense multi-sublimateur, nutri régénérant, allie couleur glamour et protection jeunesse. Sa texture onguent-fondant, répare les lèvres en profondeur tout en les rehaussant d’un éclat pimpant et naturel.",
//										"Une fragrance sensuelle pour un homme au regard captivant. Une fragrance orientale boisée aux accords à la fois virils et sensuels : fleur d’olivier, fève tonka et bois de gaïac.Un flacon couleur nuit inspiré du célèbre smoking Giorgio Armani.",
//										"L'arme du nouveau séducteur s'appelle 1 MILLION. Le flacon, qui reprend la forme d'un irrésistible lingot d'or, d'inspiration typographique Far West, est le nouvel objet emblématique de l'homme Rabanne. Objet de désir suprême, ce lingot abrite un jus tout aussi osé.",										
//										};
//	public static float[] prix={130,
//								198 ,
//								85,
//								179,
//								111,								
//								};
//	public static int[] urlImage= {R.drawable.image_1,
//									R.drawable.image_2,
//									R.drawable.image_3,
//									R.drawable.image_4,
//									R.drawable.image_5,								
//								};
//	public static int[] note= {0,
//								0,
//								0,
//								0,
//								0,					
//								};
//	
//	public static List<Produit> getProduitList()
//	{
//		List<Produit> produits = new ArrayList<Produit>();
//		for (int i = 0; i < nom.length; i++) {
//			Produit p = new Produit();
//			p.setId(i);
//			p.setNom(nom[i]);
//			p.setPrix(prix[i]);
//			p.setNote(note[i]);
//			p.setUrlImage(urlImage[i]);
//			p.setDescription(description[i]);
//			
//			produits.add(p);
//		}
//		
//		return produits;
//	}
//}
