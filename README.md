# Quick Harvest [plugin for minecraft 1.13+] <img src="https://github.com/teacondemns/static.pexty.xyz/blob/main/src/emoji/animated/minecraft.gif?raw=true" height="35"/>
Plugin that let you harvest the plants by pressing <img src="https://github.com/teacondemns/static.pexty.xyz/blob/main/src/icon/controller/mouse-right.png?raw=true" height="20"/> and automatic replant it again!
- Quick harvest by dispenser! (You can disable this feature in plugin config)
- To quick harvest press <img src="https://github.com/teacondemns/static.pexty.xyz/blob/main/src/icon/controller/mouse-right.png?raw=true" height="20"/>! (You can disable this feature in plugin config)
- Expand the list of supported plants at your discretion! Currently supported:
  - `minecraft:wheat`
  - `minecraft:beetroots`
  - `minecraft:potatoes`
  - `minecraft:carrots`
  - `minecraft:nether_wart`
  - `minecraft:cocoa`

### Preview
![](https://user-images.githubusercontent.com/83653555/176735339-61d07497-8f9f-406f-9993-db764d1f525f.png)
![](https://user-images.githubusercontent.com/83653555/177048584-210fc4b7-a637-42b7-b846-94989fccadc4.png)

### Settings in `config.yml`
```yml
sound: minecraft:block.composter.ready  # played sound when quick harvest

feature:
  player: true  # quick harvest by right-click
  dispenser: true  # quick harvest by dispenser

reason:
  minecraft:wheat_seeds:  # item in hand
    target: minecraft:wheat  # harvest block (works when harvest age equals max age)

  minecraft:beetroot_seeds:
    target: minecraft:beetroots

  minecraft:potato:
    target: minecraft:potatoes

  minecraft:carrot:
    target: minecraft:carrots

  minecraft:nether_wart:
    target: minecraft:nether_wart

  minecraft:cocoa_beans:
    target: minecraft:cocoa
```

[[Download latest version]](https://github.com/TeaCondemns/quick-harvest-plugin/releases/tag/normal-functionality)
