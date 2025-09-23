package com.example.dungeon.core;

import com.example.dungeon.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Game {
    private final GameState state = new GameState();
    private final Map<String, Command> commands = new LinkedHashMap<>();

    static {
        WorldInfo.touch("Game");
    }

    public Game() {
        registerCommands();
        bootstrapWorld();
    }

    private void registerCommands() {
        commands.put("help", (ctx, a) -> System.out.println("Команды: " + String.join(", ", commands.keySet())));
        commands.put("gc-stats", (ctx, a) -> {
            Runtime rt = Runtime.getRuntime();
            long free = rt.freeMemory(), total = rt.totalMemory(), used = total - free;
            System.out.println("Память: used=" + used + " free=" + free + " total=" + total);
        });
        commands.put("about", (ctx, a) -> {
            System.out.println("The game dungeon");
        });
        commands.put("look", (ctx, a) -> System.out.println(ctx.getCurrent().describe()));
        commands.put("move", (ctx, a) -> {
            if (a.size() != 1) {
                throw new InvalidCommandException("Используйте: move <направление>");
            }
            String dir = a.get(0).toLowerCase(Locale.ROOT);
            Room currentRoom = ctx.getCurrent();
            Map<String, Room> neighbors = currentRoom.getNeighbors();
            Room nextRoom = neighbors.get(dir);
            if (nextRoom == null) {
                throw new InvalidCommandException("Нет выхода в этом направлении: " + dir);
            }
            ctx.setCurrent(nextRoom);
            System.out.println("Вы перешли в: " + nextRoom.getName());
            System.out.println(nextRoom.describe());
        });
        commands.put("take", (ctx, a) -> {
            if (a.isEmpty()) {
                throw new InvalidCommandException("Используйте: take <имя предмета>");
            }
            String itemName = String.join(" ", a);
            Room room = ctx.getCurrent();
            Optional<Item> itemOpt = room.getItems().stream()
                    .filter(i -> i.getName().equalsIgnoreCase(itemName))
                    .findFirst();
            if (itemOpt.isEmpty()) {
                throw new InvalidCommandException("Предмет не найден в комнате: " + itemName);
            }
            Item item = itemOpt.get();
            room.getItems().remove(item);
            ctx.getPlayer().getInventory().add(item);
            System.out.println("Взято: " + item.getName());
        });
        commands.put("inventory", (ctx, a) -> {
            List<Item> inv = ctx.getPlayer().getInventory();
            if (inv.isEmpty()) {
                System.out.println("Инвентарь пуст.");
                return;
            }
            Map<Class<? extends Item>, List<Item>> grouped = inv.stream()
                    .collect(Collectors.groupingBy(Item::getClass));
            grouped.forEach((cls, items) -> {
                System.out.println(cls.getSimpleName() + " (" + items.size() + "):");
                Map<String, List<Item>> byName = items.stream()
                        .collect(Collectors.groupingBy(Item::getName));
                byName.forEach((name, sameNameItems) -> {
                    System.out.println("  - " + name);
                });
            });
        });
        commands.put("use", (ctx, a) -> {
            if (a.isEmpty()) {
                throw new InvalidCommandException("Используйте: use <имя предмета>");
            }
            String itemName = String.join(" ", a);
            Player p = ctx.getPlayer();
            Optional<Item> itemOpt = p.getInventory().stream()
                    .filter(i -> i.getName().equalsIgnoreCase(itemName))
                    .findFirst();
            if (itemOpt.isEmpty()) {
                throw new InvalidCommandException("Предмет не найден в инвентаре: " + itemName);
            }
            Item item = itemOpt.get();
            item.apply(ctx);
        });
        commands.put("fight", (ctx, a) -> {
            Player player = ctx.getPlayer();
            Room room = ctx.getCurrent();
            Monster monster = room.getMonster();
            if (monster == null) {
                throw new InvalidCommandException("В текущей комнате нет монстра для боя.");
            }
            System.out.println("Вы начинаете бой с " + monster.getName() + " (ур. " + monster.getLevel() + ")");

            int damage = player.getAttack();
            monster.setHp(monster.getHp() - damage);
            System.out.println("Вы бьёте " + monster.getName() + " на " + damage + ". HP монстра: " + Math.max(0, monster.getHp()));
            if (monster.getHp() <= 0) {
                System.out.println("Вы победили " + monster.getName() + "!");
                // Выдать лут
                List<Item> lootItems = monster.getLoot();
                if (!lootItems.isEmpty()) {
                    player.getInventory().addAll(lootItems);
                    System.out.println("После победы выпало:");
                    for (Item item : lootItems) {
                        System.out.println("- " + item.getName());
                    }
                }
                room.setMonster(null);

            } else {
            int monsterDamage = Math.max(1, monster.getLevel());
            player.setHp(player.getHp() - monsterDamage);
            System.out.println("Монстр отвечает на " + monsterDamage + ". Ваше HP: " + Math.max(0, player.getHp()));
            }
            if (player.getHp() <= 0) {
                System.out.println("Вы погибли...");
                System.exit(0);
            }

        });
        commands.put("save", (ctx, a) -> SaveLoad.save(ctx));
        commands.put("load", (ctx, a) -> SaveLoad.load(ctx));
        commands.put("scores", (ctx, a) -> SaveLoad.printScores());
        commands.put("exit", (ctx, a) ->

        {
            System.out.println("Пока!");
            System.exit(0);
        });
    }

    private void bootstrapWorld() {
        Player hero = new Player("Герой", 20, 5);
        state.setPlayer(hero);

        Room square = new Room("Площадь", "Каменная площадь с фонтаном.");
        Room forest = new Room("Лес", "Шелест листвы и птичий щебет.");
        Room cave = new Room("Пещера", "Темно и сыро.");
        square.getNeighbors().put("north", forest);
        forest.getNeighbors().put("south", square);
        forest.getNeighbors().put("east", cave);
        cave.getNeighbors().put("west", forest);

        forest.getItems().add(new Potion("Малое зелье", 5));
        forest.setMonster(new Monster("Волк", 1, 15, List.of(new Weapon("Меч", 2), new Potion("Среднее зелье", 8))));

        state.setCurrent(square);
    }

    public void run() {
        System.out.println("DungeonMini (TEMPLATE). 'help' — команды.");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.print("> ");
                String line = in.readLine();
                if (line == null) break;
                line = line.trim();
                if (line.isEmpty()) continue;
                List<String> parts = Arrays.asList(line.split("\s+"));
                String cmd = parts.getFirst().toLowerCase(Locale.ROOT);
                List<String> args = parts.subList(1, parts.size());
                Command c = commands.get(cmd);
                try {
                    if (c == null) throw new InvalidCommandException("Неизвестная команда: " + cmd);
                    c.execute(state, args);
                    state.addScore(1);
                } catch (InvalidCommandException e) {
                    System.out.println("Ошибка: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Непредвиденная ошибка: " + e.getClass().getSimpleName() + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода: " + e.getMessage());
        }
    }
}
