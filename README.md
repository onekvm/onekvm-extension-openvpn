# OneKVM openvpn extension

Installs openvpn and onekvm-kernel-module-tun. OpenVPN interfaces stay in OneKVM network settings; this package does not run a resident service.

The manifest, health command, assets, and payload installation rules belong to
this repository. `oe-recipes/` contains its OpenEmbedded layer; the distro pins
both source and layer with `kas/components.lock`.

Build the IPK from `onekvm-distro`:

```sh
make ONEKVM_MACHINE=onekvm-nanokvm build extension openvpn
```

To stage the payload locally:

```sh
make install DESTDIR=/tmp/onekvm-openvpn-stage PREFIX=/data/onekvm/apps/openvpn/0.1.1 VERSION=0.1.1 ARCH=riscv64
```

The extension remains disabled by default. OneKVM settings own configuration;
the health command does not change network interfaces or firewall policy.

License: `MIT` for the extension; MIT for OE layer metadata.
