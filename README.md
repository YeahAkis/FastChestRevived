# FastChest Revived

Chests in Minecraft are block entities. Every chest you can see gets drawn by its own renderer every single frame, lid animation and all, so a storage room with a few hundred of them can tank your FPS.

FastChest Revived makes chests normal blocks on your side. They get baked into the chunk like stone or planks, so a wall of chests costs about the same as a wall of planks.

## What it does

- Chests, trapped chests, ender chests and copper chests render as plain block models
- Your client never creates block entities for them, so there's no renderer and no ticker running for each chest
- Breaking cracks show up on chests again
- Opening chests, sounds and everything else work like normal

The one thing you lose is the lid opening animation. If you want static chests that still animate, check out [Enhanced Block Entities](https://modrinth.com/mod/ebe).

## Client side only

You only need it on your client. It works on any server, vanilla or modded, and the server doesn't need to have it. Nothing about the world or other players changes, chests just look like chests that don't open.

## Versions

Every Minecraft line has its own branch, `master` is always the newest one.

| Branch | Minecraft | Loaders |
| --- | --- | --- |
| `master` / `26-3` | 26.3 | Fabric, Forge, NeoForge |
| `26-2` | 26.2 | Fabric, Forge, NeoForge |
| `26-1` | 26.1 to 26.1.2 | Fabric, Forge, NeoForge |
| `1-21-9` | 1.21.9 to 1.21.11 | Fabric, Forge, NeoForge |
| `1-21` | 1.21 to 1.21.8 | Fabric, Forge, NeoForge |
| `1-20-5` | 1.20.5 and 1.20.6 | Fabric, Forge, NeoForge |
| `1-20` | 1.20 to 1.20.4 | Fabric, Forge (1.20.1), NeoForge (1.20.2 to 1.20.4) |
| `1-19-3` | 1.19.3 and 1.19.4 | Fabric, Forge (1.19.4) |
| `1-19` | 1.19 to 1.19.2 | Fabric, Forge (1.19.2) |
| `1-18` | 1.18 to 1.18.2 | Fabric, Forge (1.18.2) |
| `1-17` | 1.17 and 1.17.1 | Fabric, Forge (1.17.1) |
| `1-16` | 1.16 to 1.16.5 | Fabric, Forge (1.16.5) |

On Fabric you also need Fabric API.

## Sodium and Embeddium

Works fine with both, since chests are just normal blocks now. In a quick test on 26.3 a big chest room went from around 140-200 FPS with plain Fabric to 700-1000 with Sodium and FastChest Revived.

## Resource packs

The models use the normal chest textures from `textures/entity/chest`, so packs that retexture chests work fine.

If a pack ships its own chest blockstates it will override the mod. To fix that, delete `chest.json`, `trapped_chest.json`, `ender_chest.json` and the copper chest ones from `assets/minecraft/blockstates` in the pack.

## Building

Each loader is its own Gradle project:

```
cd fabric
./gradlew build
```

Same for `forge` and `neoforge`. The jar ends up in `build/libs`.

## Credits

This started as a fork of [FastChest](https://github.com/FakeDomi/FastChest) by Domi, MIT licensed.
