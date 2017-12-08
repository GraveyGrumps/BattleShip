export class Tile {
    shipPart: Number[];
    firedOn: Boolean;
    hit: Boolean;

    constructor() {
        this.firedOn = false;
        this.hit = false;
    }
}
