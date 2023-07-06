package paquete;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
	static List<Game> inventory = new ArrayList<>();
	static List<Sale> sales = new ArrayList<>();
	static int earnings = 0;
	public static void main (String[] args) {
		Scanner input = new Scanner(System.in);
		
		
		Game game = new Game("God of War", 50000, "Accion", "PlayStation 5", 50);
		AddGame(game);
		game = new Game("Super Mario Bros", 50000, "Plataformas", "Nintendo Switch", 100);
		AddGame(game);
		game = new Game("Final Fantasy XVI", 50000, "RPG", "PlayStation 5", 100);
		AddGame(game);
		game = new Game("Hi-Fi Rush", 30000, "Hack and Slash", "Xbox Series", 100);
		AddGame(game);
		game = new Game("The Legend of Zelda", 50000, "Aventura", "Nintendo Switch", 100);
		AddGame(game);
		
		game.genre = "Adventure";
		System.out.println(game.genre);
			
		while(true) {
			
			System.out.println("¿[C]liente o [A]dministrador?");
			System.out.println("Ingrese la letra [C] o [A] (O ingrese [X] para salir):");
			String session = input.nextLine();
			
			if(session.equals("A")) {
				System.out.println("Has ingresado como administrador");
				while(true) {
					System.out.println("Selecciona que quieres hacer");
					System.out.println("[1] Comprar juego");
					System.out.println("[2] Vender juego");
					System.out.println("[3] Ver catálogo");
					System.out.println("[4] Generar informe");
					System.out.println("[5] Volver atras");
					
					String action= input.nextLine();
					if(action.equals("1")) {
						System.out.println("Ingresa el nombre del juego a comprar:");
						String gameName = input.nextLine();
						System.out.println("Ingresa la cantidad del juego a comprar:");
						int gameAmount = Integer.parseInt(input.nextLine());
						if(BuyGameAdmin(gameName,gameAmount) == false) {
							System.out.println("Nombre de juego no encontrado (Recuerda diferenciar entre minusculas y mayusculas)");
						}
						else {
							System.out.println("Compra realizada exitosamente");
						}
					}
					else if(action.equals("2")) {
						System.out.println("Ingresa el nombre del juego a vender:");
						String gameName = input.nextLine();
						System.out.println("Ingresa la cantidad del juego a vender:");
						int gameAmount = Integer.parseInt(input.nextLine());
						if(SellGame(gameName,gameAmount) == false) {
							System.out.println("Nombre de juego no encontrado (Recuerda diferenciar entre minusculas y mayusculas)");
						}
						else {
							System.out.println("Venta realizada exitosamente");
						}
					}
					else if(action.equals("3")) {
						ShowCatalogue();
					}
					else if(action.equals("4")) {
						ShowSales();
					}
					else if(action.equals("5")) {
						break;
					}
					else{
						System.out.println("Ingresa un número válido");
					}
				}
			}
			
			else if(session.equals("C")) {
				System.out.println("Has ingresado como cliente");
				while(true) {
					System.out.println("Selecciona que quieres hacer");
					System.out.println("[1] Comprar juego");
					System.out.println("[2] Ver catálogo");
					System.out.println("[3] Volver atras");
					
					String action= input.nextLine();
					if(action.equals("1")) {
						System.out.println("Ingresa el nombre del juego a comprar:");
						String gameName = input.nextLine();
						System.out.println("Ingresa la cantidad del juego a comprar:");
						int gameAmount = Integer.parseInt(input.nextLine());
						if(BuyGameClient(gameName,gameAmount) == false) {
							System.out.println("Nombre de juego no encontrado (Recuerda diferenciar entre minusculas y mayusculas)");
						}
						else {
							System.out.println("Compra realizada exitosamente");
						}
					}
					else if(action.equals("2")) {
						ShowCatalogue();
					}
					else if(action.equals("3")) {
						break;
					}
					else{
						System.out.println("Ingresa un número válido");
					}
				}
			}
			
			else if(session.equals("X")) {
				break;
			}
			
			else{
				System.out.println("Ingresa un input válido");
			}
		}
		input.close();
	}
	
	public static boolean BuyGameAdmin(String title, int amount) {
		for(Game  game: inventory) {
			if(game.title.equals(title)) {
				game.amount += amount;
				earnings -= amount * game.price / 2;
				AddSale(game, amount, earnings);
				return true;
			}
		}
		return false;
	}
	
	public static boolean BuyGameClient(String title, int amount) {
		for(Game  game: inventory) {
			if(game.title.equals(title)) {
				if(game.amount >= amount) {
					game.amount -= amount;
					earnings += amount * game.price;
					AddSale(game, amount, earnings);
					return true;
				}
				else {
					return false;
				}
			}
		}
		return false;
	}
	
	public static boolean SellGame(String title, int amount) {
		for(Game  game: inventory) {
			if(game.title.equals(title)) {
				if(game.amount >= amount) {
					game.amount -= amount;
					earnings += amount * game.price;
					AddSale(game, amount, earnings);
					return true;
				}
				else {
					return false;
				}
			}
		}
		return false;
	}
	
	public static void AddGame(Game game) {
		inventory.add(game);
	}
	
	public static void AddSale(Game game, int amount, int earnings) {
		Sale sale = new Sale(game, amount, earnings);
		sales.add(sale);
	}
	
	public static void ShowSales() {
		System.out.println("Reporte de compras/ventas");
		for (Sale sale : sales) {
			if(sale.earning < 0) {
				System.out.println("Compra");
			}
			else System.out.println("Compra");
			System.out.println("Título: " + sale.game.title);
			System.out.println("Cantidad comprada/vendida: " + sale.amount);
			System.out.println("Ganancia: " + sale.earning);
			System.out.println("");
		}
		System.out.println("Ganancias totales: " + Integer.toString(earnings));
		System.out.println("");
	}
	
	public static void ShowCatalogue() {
		Scanner input = new Scanner(System.in);
		for (Game game : inventory ) {
			System.out.println("Título: " + game.title);
			System.out.println("Precio: " + Integer.toString(game.price));
			System.out.println("Genero: " + game.genre);
			System.out.println("Plataforma: " + game.platform);
			System.out.println("Cantidad disponible: " + Integer.toString(game.amount));
			System.out.println("");
		}
	}
}
