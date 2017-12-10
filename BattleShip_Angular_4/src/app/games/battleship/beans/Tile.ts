export class Tile {
    shipPart: number[];
    firedOn: Boolean;
    hit: Boolean;

    constructor() {
        this.firedOn = false;
        this.hit = false;
    }
}
