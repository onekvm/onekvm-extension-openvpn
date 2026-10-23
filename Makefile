PREFIX ?= /usr/local/lib/onekvm/extensions/openvpn
VERSION ?= 0.1.1
ARCH ?= unknown

.PHONY: install
install:
	install -d $(DESTDIR)$(PREFIX)/bin
	install -m 0755 onekvm-openvpn $(DESTDIR)$(PREFIX)/bin/onekvm-openvpn
	install -d $(DESTDIR)$(PREFIX)/web
	install -m 0644 web/logo.svg $(DESTDIR)$(PREFIX)/web/logo.svg
	sed -e 's/@VERSION@/$(VERSION)/g' -e 's/@ARCH@/$(ARCH)/g' manifest.json.in > $(DESTDIR)$(PREFIX)/manifest.json
	chmod 0644 $(DESTDIR)$(PREFIX)/manifest.json
