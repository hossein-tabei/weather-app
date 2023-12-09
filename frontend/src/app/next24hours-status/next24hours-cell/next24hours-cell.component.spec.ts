import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Next24hoursCellComponent } from './next24hours-cell.component';

describe('Next24hoursCellComponent', () => {
  let component: Next24hoursCellComponent;
  let fixture: ComponentFixture<Next24hoursCellComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Next24hoursCellComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(Next24hoursCellComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
